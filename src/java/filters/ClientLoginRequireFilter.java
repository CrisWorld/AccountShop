/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Category;
import models.User;
import untils.Account;
import untils.CartDAO;
import java.sql.*;
import models.Database;


/**
 *
 * @author quoch
 */
public class ClientLoginRequireFilter implements Filter {
    
    private static final boolean debug = true;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;
    
    public ClientLoginRequireFilter() {
    }

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public List<Category> getCategories() throws SQLException {
        Connection connection = Database.getConnect();
        String query = "SELECT id, name, banner, [desc] AS description, parent_id FROM product_categories";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        // Create a map to hold categories by their id
        Map<Integer, Category> categoryMap = new HashMap<>();
        List<Category> categories = new ArrayList<>();
        List<Category> finalCategories = new ArrayList<>();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String banner = resultSet.getString("banner");
            String description = resultSet.getString("description");
            Integer parentId = resultSet.getObject("parent_id") != null ? resultSet.getInt("parent_id") : null;
            Category category = new Category(id, name, description, banner, new ArrayList<>(), parentId);
            categories.add(category);
            if (parentId == null) {
                categoryMap.put(id, category);
            }
        }
        for(Category category: categories){
            System.out.println(category.getName());
            if(category.getParentId() != null) categoryMap.get(((int)category.getParentId())).getChilds().add(category);
            else finalCategories.add(category);
        }

        return finalCategories;
    }
    
    public void initCategory(HttpServletRequest request, HttpServletResponse response) throws SQLException{
        ArrayList<Category> categories = (ArrayList<Category>) getCategories();
        request.setAttribute("categories", categories);
    }
    
    public boolean isExclude(String uri){
        List<String> excludedUrls = Arrays.asList("/client/assets", "/admin/", "/auth/client", "/auth/admin");
        boolean shouldExclude = excludedUrls.stream().anyMatch(uri::startsWith);
        return shouldExclude;
    }
    
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        if (debug) {
            log("ClientLoginRequireFilter:doFilter()");
        }
                
        Throwable problem = null;
        try {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String uri = httpRequest.getRequestURI();
            boolean isExclude = isExclude(uri);
            HttpSession session = httpRequest.getSession(true);
            if(isExclude){
                chain.doFilter(request, response);
            } else if(session.getAttribute("client") != null){
                User client = (User) session.getAttribute("client");
                User checkClient = Account.getAccountByUserName(client.getUsername());
                if(!checkClient.isIsAdmin()) {
                    initCategory(httpRequest, httpResponse);
                    // get number of item in card;
                    CartDAO cartRepo = new CartDAO();
                    if(checkClient.getCartId()==null) httpRequest.setAttribute("numberOfItemInCart", 0);
                    else httpRequest.setAttribute("numberOfItemInCart", cartRepo.getCartById(checkClient.getCartId()).getProducts().size());
                    
                    chain.doFilter(request, response);
                }
                else {
                    httpResponse.sendRedirect("/auth/client");
                }
            } else {
                httpResponse.sendRedirect("/auth/client");
            }
        } catch (Throwable t) {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }
        
        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if (problem != null) {
            if (problem instanceof ServletException) {
                throw (ServletException) problem;
            }
            if (problem instanceof IOException) {
                throw (IOException) problem;
            }
            sendProcessingError(problem, response);
        }
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {                
                log("ClientLoginRequireFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("ClientLoginRequireFilter()");
        }
        StringBuffer sb = new StringBuffer("ClientLoginRequireFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }
    
    private void sendProcessingError(Throwable t, ServletResponse response) {
        String stackTrace = getStackTrace(t);        
        
        if (stackTrace != null && !stackTrace.equals("")) {
            try {
                response.setContentType("text/html");
                PrintStream ps = new PrintStream(response.getOutputStream());
                PrintWriter pw = new PrintWriter(ps);                
                pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); //NOI18N

                // PENDING! Localize this for next official release
                pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");                
                pw.print(stackTrace);                
                pw.print("</pre></body>\n</html>"); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        } else {
            try {
                PrintStream ps = new PrintStream(response.getOutputStream());
                t.printStackTrace(ps);
                ps.close();
                response.getOutputStream().close();
            } catch (Exception ex) {
            }
        }
    }
    
    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }
    
    public void log(String msg) {
        filterConfig.getServletContext().log(msg);        
    }
    
}
