<%-- 
    Document   : login
    Created on : 3 Jul 2024, 12:10:42
    Author     : quoch
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <title>JSP Page</title>
        <!-- Font Awesome -->
        <link
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
          rel="stylesheet"
        />
        <!-- Google Fonts -->
        <link
          href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
          rel="stylesheet"
        />
        <!-- MDB -->
        <link
          href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.3.2/mdb.min.css"
          rel="stylesheet"
        />
    </head>
    <body>
        <section class="d-flex" style="height: 100vh">
            <!-- Jumbotron -->
            <div class="py-5 px-md-5 text-center text-lg-start h-100 w-100 d-flex justify-content-center align-item-center" style="background-color: hsl(0, 0%, 96%)">
              <div class="container d-flex justify-content-center align-item-center">
                <div class="row gx-lg-5 align-items-center">
                  <div class="col-lg-6 mb-5 mb-lg-0">
                    <h1 class="my-5 display-3 fw-bold ls-tight">
                      The best offer <br />
                      <span class="text-primary">for your business</span>
                    </h1>
                    <p style="color: hsl(217, 10%, 50.8%)">
                      Lorem ipsum dolor sit amet consectetur adipisicing elit.
                      Eveniet, itaque accusantium odio, soluta, corrupti aliquam
                      quibusdam tempora at cupiditate quis eum maiores libero
                      veritatis? Dicta facilis sint aliquid ipsum atque?
                    </p>
                  </div>

                  <div class="col-lg-6 mb-5 mb-lg-0">
                    <div class="card">
                      <div class="card-body py-5 px-md-5">
                        <form action="/auth/client?action=register" method="post">
                          <!-- 2 column grid layout with text inputs for the first and last names -->
                          <div class="row">
                            <div class="col-md-12 mb-4">
                              <div data-mdb-input-init class="form-outline">
                                <input required name="fullname" type="text" id="form3Example1" class="form-control" />
                                <label class="form-label" for="form3Example1">Full name</label>
                              </div>
                            </div>
                          </div>

                          <!-- Email input -->
                          <div data-mdb-input-init class="form-outline mb-4">
                            <input name="username" type="text" required id="form3Example3" class="form-control" />
                            <label class="form-label" for="form3Example3">Username</label>
                          </div>

                          <!-- Password input -->
                          <div data-mdb-input-init class="form-outline mb-4">
                            <input name="password" required type="password" id="form3Example4" class="form-control" />
                            <label class="form-label" for="form3Example4">Password</label>
                          </div>

                          <!-- Submit button -->
                          <button id="btn-submit" type="submit" data-mdb-button-init data-mdb-ripple-init class="btn btn-primary btn-block mb-4">
                            Sign up
                          </button>
                        </form>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- Jumbotron -->
          </section>
        <script
            type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.3.2/mdb.umd.min.js"
        ></script>
        <script>
            const btn = document.getElementById("btn-submit");
            const form  = document.querySelector("form");
            btn.onclick = () => {
                console.log(form);
                form.submit();
            }
        </script>
    </body>
</html>
