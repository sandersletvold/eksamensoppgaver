function reg() {
    $("#feilLID").html("");
    $("#feilEIER").html("");
    $("#feilVEKT").html("");
    $("#feilVOLUM").html("");
    $("#generelt").html("");

    const pakke = {
        LID : $("#LID").val(),
        EIER : $("#EIER").val(),
        VEKT : $("#VEKT").val(),
        VOLUM : $("#VOLUM").val()
    };

    const LIDregex = /^[0-9]+$/;
    const EIERregex = /^[A-Za-z]+$/;
    const VEKTVOLUMregex = /^[0-9.]+$/;

    let gyldig = true;

    if (!LIDregex.test(pakke.LID)) {
        gyldig = false;
        $("#feilLID").html("Feil ved inout av LID");
    }
    if (!EIERregex.test(pakke.EIER)) {
        gyldig = false;
        $("#feilEIER").html("Feil ved inout av EIER");
    }
    if (!VEKTVOLUMregex.test(pakke.VEKT)) {
        gyldig = false;
        $("#feilVEKT").html("Feil ved inout av VEKT");
    }
    if (!VEKTVOLUMregex.test(pakke.VOLUM)) {
        gyldig = false;
        $("#feilVOLUM").html("Feil ved inout av VOLUM");
    }

    if (gyldig) {
        $.post("/lagrepakke", pakke, function (){
            $("#feilLID").html("");
            $("#feilEIER").html("");
            $("#feilVEKT").html("");
            $("#feilVOLUM").html("");
            $("#generelt").html("Pakke sendt til server!");
        });
    } else {
        $("#generelt").html("Kunne ikke sende pakke til server");
    }
}