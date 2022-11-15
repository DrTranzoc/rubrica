function inviaContatto(reqType){


	var nome = document.getElementById("nome").value;
    var cognome = document.getElementById("cognome").value;
    var indirizzo = document.getElementById("indirizzo").value;
    var telefono = document.getElementById("telefono").value;
    var eta = document.getElementById("eta").value;


    var params = new reqParams("");
    params.addParams("nome",nome)
          .addParams("cognome",cognome)
          .addParams("indirizzo",indirizzo)
          .addParams("telefono",telefono)
          .addParams("eta",eta)

    if(reqType == 1){
        var id = document.getElementById("contattoID").getAttribute("contactId");
        alert("Stai per modificare il contatto : "+id)
        params.addParams("id",id)
    }
    else{
        alert("Stai per aggiungere un nuovo contatto.")
    }

    params.addParams("reqType",reqType)
    
    //Chiama la servlet passando i parametri ottenuti
    gestisciContattoReq("gestisciContatto", params.getParams);
}


function gestisciContattoReq(url , params) {
    var http = sendRequest(url);
    
    http.onreadystatechange = function() {
        //Gestisci i vari response
        if(http.readyState == 4 && http.status == 200) {

			if(http.responseText == "-1"){
                alert("E' occorso qualche problema col server. Riprovare.");
            }
			else{
        		location.href = http.responseText;
        	}
        }
    }
    
    http.send(params);
}