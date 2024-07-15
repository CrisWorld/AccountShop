console.log(window.location.pathname);
$('li a').removeClass("active");
$('li').removeClass("mm-active");
$('li[root-href="'+window.location.pathname+'"]');
$('li:has(>a[href="'+window.location.pathname+window.location.search+'"])').toggleClass("mm-active");
$('a[href="'+window.location.pathname+window.location.search+'"]').addClass("active");

