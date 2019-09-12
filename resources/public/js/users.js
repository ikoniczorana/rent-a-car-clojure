$(document).ready(function () {
    $(".delete").on('click', function () {
        var row = $(this).closest('tr');
        console.log("CLick");
        console.log("Id ", row.attr("id"));
        $("#deleteuser").val(row.attr("id"));
    });


    $("#delete_button").on('click', function () {
        var id = $("#deleteuser").val();
        $.ajax({
            type: "POST",
            url: "/deleteuser",
            data: {
                id: id
            },
            success: function (data) {
                $('#' + id).remove();
                showMessageInfo("success", data.message, "message_div");
            },
            error: function (error) {
                var message = "";
                error.responseJSON.map(function (item) {
                    message += item[1] + ".";
                });
                showMessageInfo("danger", message, "message_divs");
            }
        });
    });
});
