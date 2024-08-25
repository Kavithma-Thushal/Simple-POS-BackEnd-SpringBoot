/**
 * @author : Kavithma Thushal
 * @project : Spring-Boot-POS
 * @since : 7:05 AM - 6/18/2024
 **/

function customerValidation() {

    // Customer ID validation
    $("#txtCustomerId").on('input', function () {
        let value = $(this).val();
        let pattern = /^C\d{2}-\d{3}$/;
        if (pattern.test(value)) {
            $(this).removeClass('border-danger').addClass('border-success');
            $("#txtCusIdError").text('');
        } else {
            $(this).removeClass('border-success').addClass('border-danger');
            $("#txtCusIdError").text('Customer ID format must be "C00-001", "C12-345"');
        }
    });

    // Customer Name validation
    $("#txtCustomerName").on('input', function () {
        let value = $(this).val();
        let pattern = /^[A-Za-z\s'-]{4,}$/;
        if (pattern.test(value)) {
            $(this).removeClass('border-danger').addClass('border-success');
            $("#txtCusNameError").text('');
        } else {
            $(this).removeClass('border-success').addClass('border-danger');
            $("#txtCusNameError").text('Name must contain at least 4 letters');
        }
    });

    // Customer Address validation
    $("#txtCustomerAddress").on('input', function () {
        let value = $(this).val();
        let pattern = /^[A-Za-z\s'-]{4,}$/;
        if (pattern.test(value)) {
            $(this).removeClass('border-danger').addClass('border-success');
            $("#txtCusAddressError").text('');
        } else {
            $(this).removeClass('border-success').addClass('border-danger');
            $("#txtCusAddressError").text('Address must contain at least 4 letters');
        }
    });

    // Customer Salary validation
    $("#txtCustomerSalary").on('input', function () {
        let value = $(this).val();
        let number = parseFloat(value);
        if (!isNaN(number) && number >= 0) {
            $(this).removeClass('border-danger').addClass('border-success');
            $("#txtCusSalaryError").text('');
        } else {
            $(this).removeClass('border-success').addClass('border-danger');
            $("#txtCusSalaryError").text('Salary must be a positive value or zero');
        }
    });
}

function resetCustomerBorders() {
    $("#txtCustomerId").removeClass('border-danger border-success');
    $("#txtCustomerName").removeClass('border-danger border-success');
    $("#txtCustomerAddress").removeClass('border-danger border-success');
    $("#txtCustomerSalary").removeClass('border-danger border-success');

    $("#txtCusIdError").text('');
    $("#txtCusNameError").text('');
    $("#txtCusAddressError").text('');
    $("#txtCusSalaryError").text('');
}