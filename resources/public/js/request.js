$(document).ready(function () {
    $(".update").on('click', function () {
          var row = $(this).closest('tr');
          $("#requestid").val(row.attr("id"));
          $("#fromdate").val(row.find(".fromdate").text());
          $("#todate").val(row.find(".todate").text());
      });
        $("#edit_button").on('click', function () {
                var requestid = $("#requestid").val();
                var fromdate = $("#fromdate").val();
                var todate = $("#todate").val();

                $.ajax({
                   type: "POST",
                    url: "/editRequest",
                    data: {
                      requestid : requestid,
                      fromdate : fromdate,
                      todate : todate
                    },
                    dataType: 'json',
                    success: function(data) {
                      setTimeout(function () {
                        $("#edit").modal("toggle");
                        window.location = "/requests";
                        }, 1000);
                        },
                   error: function(data) {
                        setTimeout(function () {
                            $("#edit").modal("toggle");
                            window.location = "/requests";
                        }, 1000);
                        }
                });
            });
    });

