function showNavbarSettingsTab() {

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
