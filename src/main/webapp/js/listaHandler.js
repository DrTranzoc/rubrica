function modificaContatto(element){
    var id = $(element).attr("id");
    var params = new reqParams("");
    params.addParams("id",id);
    richiediModifica("editor.jsp",params.getParams);
}

function cancellaContatto(element){
    var id = $(element).attr("id");
    var params = new reqParams("");
    params.addParams("id",id);
    inoltraCancellazione("cancellaContatto",params.getParams);
}

function inoltraCancellazione(url , params) {
    var http = sendRequest(url);
    
    http.onreadystatechange = function() {
        //Gestisci i vari response
        if(http.readyState == 4 && http.status == 200) {
	
        	if(http.responseText == "-1"){
                alert("Impossibile cancellare. Riprovare.");
            }
			else{
        		location.href = http.responseText;
        	}
        }
    }
    
    http.send(params);
}


function richiediModifica(url , params) {
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