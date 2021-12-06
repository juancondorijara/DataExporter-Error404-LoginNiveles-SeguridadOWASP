function validarDNI() {
    var dni = document.getElementById("dni").value;
    if (dni.length === 8) {
        PF('btnregistrar').enable();
//        alert("DNI COMPLETADO")
    } else {
        PF('btnregistrar').disable();
    }
}

function validarCelular() {
    var celular = document.getElementById("celular").value;
    if (celular.length === 9) {
        PF('btnregistrar').enable();
    } else {
        PF('btnregistrar').disable();
    }
}

function validarRUC() {
    var ruc = document.getElementById("ruc").value;
    if (ruc.length === 11) {
        PF('btnregistrar').enable();
    } else {
        PF('btnregistrar').disable();
    }
}

function validarCampos() {
//    var nombres = document.getElementById("nombres").value;
    var apellidos = document.getElementById("apellidos").value;
    if (apellidos.length > 5) {
        PF('txtnombres').disable();
        PF('txtapellidos').disable();
    } else {
        PF('txtnombres').enable();
        PF('txtapellidos').enable();
    }
}
