function showNavbar() {
    if(sessionStorage.getItem("isAdmin")) {
        $("#navbar").load("navbarWithSettings.html");
    } else {
        $("#navbar").load("navbar.html");
    }
}

function showNavbarInLogin() {
    $("#navbar").load("/forms/navbar.html");
}

function signOut() {
    sessionStorage.clear();
}

function inputsNotEmpty(classToBeChecked) {
    var isReadyToSubmit = true;
    $(classToBeChecked).each(function () {
        if (isEmpty($(this).val())) {
            $(this).addClass("borderRojo");
            isReadyToSubmit = false;
        } else {
            $(this).removeClass("borderRojo");
        }
    });
    return isReadyToSubmit;
}

function isEmpty(valueToBeChecked) {
    return valueToBeChecked.length <= 0;
}
