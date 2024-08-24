/**
 * @author : Kavithma Thushal
 * @project : Spring-Boot-POS
 * @since : 7:05 AM - 6/18/2024
 **/

function itemValidation() {

    // Item Code validation
    $("#txtItemCode").on('input', function () {
        let value = $(this).val();
        let pattern = /^I\d{2}-\d{3}$/;
        if (pattern.test(value)) {
            $(this).removeClass('border-danger').addClass('border-success');
            $("#txtItemCodeError").text('');
        } else {
            $(this).removeClass('border-success').addClass('border-danger');
            $("#txtItemCodeError").text('Item Code format must be "I00-001", "I12-345"');
        }
    });

    // Item Description validation
    $("#txtItemDescription").on('input', function () {
        let value = $(this).val();
        let pattern = /^[A-Za-z\s'-]{4,}$/;
        if (pattern.test(value)) {
            $(this).removeClass('border-danger').addClass('border-success');
            $("#txtItemDescriptionError").text('');
        } else {
            $(this).removeClass('border-success').addClass('border-danger');
            $("#txtItemDescriptionError").text('Description must contain at least 4 letters');
        }
    });

    // Unit Price validation
    $("#txtItemUnitPrice").on('input', function () {
        let value = $(this).val();
        let number = parseFloat(value);
        if (!isNaN(number) && number >= 0) {
            $(this).removeClass('border-danger').addClass('border-success');
            $("#txtItemUnitPriceError").text('');
        } else {
            $(this).removeClass('border-success').addClass('border-danger');
            $("#txtItemUnitPriceError").text('Unit Price must be a positive value or zero');
        }
    });

    // Qty On Hand validation
    $("#txtItemQtyOnHand").on('input', function () {
        let value = $(this).val();
        let number = parseInt(value);
        if (!isNaN(number) && number >= 0) {
            $(this).removeClass('border-danger').addClass('border-success');
            $("#txtItemQtyOnHandError").text('');
        } else {
            $(this).removeClass('border-success').addClass('border-danger');
            $("#txtItemQtyOnHandError").text('Qty On Hand must be a positive value or zero');
        }
    });
}

function resetItemBorders() {
    $("#txtItemCode").removeClass('border-danger border-success');
    $("#txtItemDescription").removeClass('border-danger border-success');
    $("#txtItemUnitPrice").removeClass('border-danger border-success');
    $("#txtItemQtyOnHand").removeClass('border-danger border-success');

    $("#txtItemCodeError").text('');
    $("#txtItemDescriptionError").text('');
    $("#txtItemUnitPriceError").text('');
    $("#txtItemQtyOnHandError").text('');
}