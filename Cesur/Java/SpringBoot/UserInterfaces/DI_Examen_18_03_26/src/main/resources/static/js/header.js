window.addEventListener('scroll', function() {
    const header = document.querySelector('header');
    const body = document.body;

    if (window.scrollY > 100) {
        header.classList.add('scrolled');
        body.classList.add('header-scrolled');
    } else {
        header.classList.remove('scrolled');
        body.classList.remove('header-scrolled');
    }
});
