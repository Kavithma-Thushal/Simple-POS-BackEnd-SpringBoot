let baseUrl = "http://localhost:8080/api/v1/customer";

$("#btnSaveCustomer").click(function () {
    let cusId = $("#txtCustomerId").val();
    let cusName = $("#txtCustomerName").val();
    let cusAddress = $("#txtCustomerAddress").val();
    let cusSalary = $("#txtCustomerSalary").val();

    const customerOb = {
        id: cusId,
        name: cusName,
        address: cusAddress,
        salary: cusSalary
    };

    $.ajax({
        url: baseUrl + "/saveCustomer",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(customerOb),
        success: function (res) {
            alert("Customer Saved Successfully...!");
        }, error: function (error) {
            alert("Customer Saved Error...!");
        }
    });
});

$("#btnSearchCustomer").click(function () {
    var searchCusId = $("#txtCustomerId").val();
    $.ajax({
        url: baseUrl + "/searchCustomer/" + searchCusId,
        method: "GET",
        success: function (res) {
            $("#txtCustomerName").val(res.name);
            $("#txtCustomerAddress").val(res.address);
            $("#txtCustomerSalary").val(res.salary);
            console.log("Customer Searched Successfully...!");
        },
        error: function (error) {
            console.log("Customer Searched Error...!");
        }
    });
});

$("#btnUpdateCustomer").click(function () {
    let cusId = $("#txtCustomerId").val();
    let cusName = $("#txtCustomerName").val();
    let cusAddress = $("#txtCustomerAddress").val();
    let cusSalary = $("#txtCustomerSalary").val();

    const customerOb = {
        id: cusId,
        name: cusName,
        address: cusAddress,
        salary: cusSalary
    };

    $.ajax({
        url: baseUrl + "/updateCustomer",
        method: "PUT",
        contentType: "application/json",
        data: JSON.stringify(customerOb),
        success: function (res) {
            alert("Customer Updated Successfully...!");
        },
        error: function (error) {
            alert("Customer Updated Error...!");
        }
    });
});

$("#btnDeleteCustomer").click(function () {
    var deletedCusId = $("#txtCustomerId").val();

    $.ajax({
        url: baseUrl + "/deleteCustomer/" + deletedCusId,
        method: "DELETE",
        success: function (res) {
            alert("Customer Deleted Successfully...!");
        },
        error: function (error) {
            alert("Customer Deleted Error...!");
        }
    });
});

$('#btnLoadAllCustomers').click(function () {
    $.ajax({
        url: baseUrl + "/loadAllCustomers",
        method: "GET",
        success: function (res) {
            res.forEach(customer => {
                let id = customer.id;
                let name = customer.name;
                let address = customer.address;
                let salary = customer.salary;

                let row = `<tr>
                    <td>${id}</td>
                    <td>${name}</td>
                    <td>${address}</td>
                    <td>${salary}</td>
                </tr>`;
                $("#customerTable").append(row);
            });
        },
        error: function (error) {
            console.log("Load All Customers Error...!");
        }
    });
});