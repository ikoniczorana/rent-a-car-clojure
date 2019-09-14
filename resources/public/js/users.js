$(document).ready(function () {
    $(".delete").on('click', function () {
        var row = $(this).closest('tr');
        $("#delete_user").val(row.attr("id"));
    });
    $(".update").on('click', function () {
          var row = $(this).closest('tr');
          $("#userid").val(row.attr("id"));
      });

    $("#delete_button").on('click', function () {
        var id = $("#delete_user").val();
       $.ajax({
       		        type: "DELETE",
       		        url: "/deleteuser",
       		        data: {
       		            userid: id
       		        },
       		        dataType: 'json',
       		        success: function(data) {
       		            window.location = "/users";
       		        },
       		        error: function(data) {
                      window.location = "/users";
                  }

       		    });
        });
        $("#edit_button").on('click', function () {
                var id = $("#userid").val();
                var password = $("#password").val();
                var assignedrole = $("#assignedrole").val();

                $.ajax({
                   type: "POST",
                    url: "/editUser",
                    data: {
                      userid : id,
                      password : password,
                      assignedrole : assignedrole
                    },
                    dataType: 'json',
                    success: function(data) {
                      setTimeout(function () {
                        $("#edit").modal("toggle");
                        window.location = "/users";
                        }, 1000);
                        },
                   error: function(data) {
                        setTimeout(function () {
                            $("#edit").modal("toggle");
                            window.location = "/users";
                        }, 1000);
                        }
                });
            });


    });

