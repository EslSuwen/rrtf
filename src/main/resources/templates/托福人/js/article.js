$(function () {
    $("#myFile").change(function (e) {
        debugger;
        var imgFile = e.currentTarget.files[0];
        var fr = new FileReader();
        fr.readAsDataURL(imgFile);
        fr.onload = function (e) {
            $(".fileUploadLabel").css({
                background: "url(" + this.result + ")",
                backgroundSize: "100% 100%"
            })
            $(".fileUploadLabel").html("")
        }
    });

    /*$("#myFile").change(function (e) {
        for (var i = 0; i < e.target.files.length; i++) {
            var file = e.target.files.item(i);
            var freader = new FileReader();
            freader.readAsDataURL(file);
            freader.onload = function (e) {
                var src = e.target.result;
                $("#userAvatar").attr("src", src);
                $("#userAvatar150").attr("src", src);
                $("#userAvatar60").attr("src", src);
                $("#userAvatar30").attr("src", src);
            }
        }
    });*/


})
