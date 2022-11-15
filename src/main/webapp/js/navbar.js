function cambiaDB(){


    var params = new reqParams("");
    params.addParams("cambiaDB","true")
          
    //Chiama la servlet passando i parametri ottenuti
    cambiaDBReq("getIndex", params.getParams);
}


function cambiaDBReq(url , params) {
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