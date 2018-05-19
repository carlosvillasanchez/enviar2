//INICIALIZACION DE VARIABLES
var mapDiv = $('#mapDiv');
var map;
var previusMarker;
var showingNetworks = "Todas";
var wifi = [];
//CUANDO SE PULSA EL ICONO SE ELIMINA EL MARKER Y SE OCULTA EL SIDENAV
$('#back').click(function(){
    if(mapDiv.hasClass('col-sm-9')){
      $("#panelOculto").toggle(1);
      mapDiv.removeClass('col-sm-9').addClass('col-sm-12');
      previusMarker.setMap(null);
      map.setZoom(6);
      document.getElementById("usr").value = "";
  }
});

//FUNCION QUE INICIALIZA EL MAPA DE LA API DE GOOGLE Y QUE MUESTRA LAS REDES DE UN PUNTO
function initMap() {
  var myLatlng = {lat: 40.400144, lng: -3.722540};

  //Creamos un mapa de la api de google
  map = new google.maps.Map(document.getElementById('map'), {
    zoom: 6,
    center: myLatlng
  });
  
  //Cuando se aplica un filtro al pulsar un checkbox
  $("input[name='optradio']").change(function(){
    if($('input[name="optradio"][class="openNetworks"]').is(':checked')){
      showingNetworks = "Abierta";
      showNetworks();
    } else if($('input[name="optradio"][class="closedNetworks"]').is(':checked')){
      showingNetworks = "Cerrada";
      showNetworks();
    } else{
      showingNetworks = "Todas";
      showNetworks();
    }
  });
  
  for (var i = 0; i < cache.length; i++) {
	    var lat = cache[i].lat;
	    var lng = cache[i].lng;

	    //Obtenemos la informacion de cada red wifi
	    $("#cache").append("<a href='#' class='list-group-item list-group-item-action list-group-item-secondary'>"+lat + ", " + lng +"</a>");
  }
  
  //Cuando se hace click en el mapa se abre un panel lateral y se añade un marker
  map.addListener('click', function(event) {
    if(mapDiv.hasClass('col-sm-12')){
      mapDiv.removeClass('col-sm-12').addClass('col-sm-9');
      $("#panelOculto").toggle(100);
    }
    showNetworks();
    var lat = event.latlng.lat();
    var lng = event.latlng.lng();
    petition(lat, lnf);
    map.setZoom(17);
    addMarker(event.latLng);
    
    
  });
}
function petition(lat, lng){
	//var lng = location.lng();
	//var lat = location.lat();
	
	$.ajax({
		  method: "GET",
		  //dataType: "json",
		  url: "http://localhost:8080/WifiWay/PointServlet?lng=" + lng + "&lat=" + lat,
		  success: function(data) {
              console.log("SUCCESS");
              console.log(data);
              wifi = data;
              showNetworks();//DEBERIAMOS HACERLO CON UNA PROMESAPERO NO PASA NA
          }
	});
	
}

//FUNCION QUE ANADE UN MARKER, CON SU LAT Y LNG
function addMarker(location) {
  if (previusMarker != null) {
    previusMarker.setMap(null);
  }
  var marker = new google.maps.Marker({
    position: location,
    map: map
  });
  previusMarker = marker;
  map.setCenter(marker.getPosition());
  document.getElementById("usr").value = marker.getPosition().lat() + ", " +  marker.getPosition().lng();
}


//FUNCION QUE ENSEÑA LAS REDES WIFI Y LAS FILTRA
function showNetworks() {
  $("#accordion").html("");
  $("#cache").html("");
  console.log(wifi);
  //$("#accordion").append("<form id='filtro'><label class='radio-inline'><input type='radio' name='optradio' class='allNetworks' checked>Todas</label><label class='radio-inline'><input type='radio' name='optradio' class='openNetworks'>Abiertas</label><label class='radio-inline'><input type='radio' name='optradio' class='closedNetworks'>Cerradas</label></form>");

  $("#filtro").css('display', "inline-block");
  for (var i = 0; i < wifi.length; i++) {
    //Obtenemos la informacion de cada red wifi
    var name = wifi[i].SSID;
    var MAC = wifi[i].MAC;
    var type = wifi[i].Type;
    var SignalStrength = wifi[i].SignalStrength;
    var tipo;

    //Se analizan que tipo de wifi es en funcion de la encriptacion
    if(type ==="OpenEnc1" || type ==="OpenEnc2" || type ==="" || type ==="OpenEnc3"){
      tipo = "Abierta";
    } else {
      tipo = "Cerrada";
    }

    //Si no coincide el filtro con el tipo de wifi se salta a la siguiente red
    if(showingNetworks !== "Todas" && tipo !== showingNetworks){
      continue;
    }

    //Se añade al grupo de paneles las redes wifi, cada una en un panel
    $("#accordion").append("<div class='panel panel-default'> <div class='panel-heading'> <h4 class='panel-title'> <a data-toggle='collapse' data-parent='#accordion' href='#collapse"+i+1+"'>" + name + " (" + tipo + ")" +  "</a> </h4></div> <div id='collapse"+ i+1+"' class='panel-collapse collapse'> <div class='panel-body'><p>MAC: " + MAC + "</p><p>Tipo: " + type + "</p><p>Potencia: " + SignalStrength + " dBm</p></div> </div> </div>");
    
  }
  for (var i = 0; i < cache.length; i++) {
      var lat = cache[i].lat;
      var lng = cache[i].lng;

      //Obtenemos la informacion de cada red wifi
      $("#cache").append("<button onclick='petition("+lat+", "+lng+")' class='list-group-item list-group-item-action list-group-item-secondary' >"+lat + ", " + lng +"</button>");
  }
}

$("#point1").click(function(){
  console.log("LLega a ");
});
var cache = [{
    "lat": 11.1111111,
    "lng": 22.2222222
  },
  {
    "lat": 12.1111111,
    "lng": 23.2222222
  },
  {
    "lat": 13.1111111,
    "lng": 24.2222222
  },
  {
    "lat": 14.1111111,
    "lng": 25.2222222
  },
  {
    "lat": 15.1111111,
    "lng": 26.2222222
  }
];
//EJEMPLO DE RESPUESTA DEL SERVIDOR PARA UN PUNTO
var wifi = [{
    "index": 1,
    "SSID": "Movistar V00001",
    "MAC": "00:00:00:00:00:01",
    "Type": "OpenEnc1",
    "SignalStrength": -61
  },
  {
    "index": 2,
    "SSID": "Movistar V00002",
    "MAC": "00:00:00:00:00:02",
    "Type": "OpenEnc2",
    "SignalStrength": -62
  },
  {
    "index": 3,
    "SSID": "Movistar V00003",
    "MAC": "00:00:00:00:00:03",
    "Type": "ClosedEnc1",
    "SignalStrength": -63
  },
  {
    "index": 4,
    "SSID": "Movistar V00004",
    "MAC": "00:00:00:00:00:04",
    "Type": "ClosedEnc2",
    "SignalStrength": -64
  },
  {
    "index": 5,
    "SSID": "Movistar V00005",
    "MAC": "00:00:00:00:00:05",
    "Type": "",
    "SignalStrength": -65
  },
  {
    "index": 6,
    "SSID": "Vodafone V00001",
    "MAC": "00:00:00:00:01:01",
    "Type": "ClosedEnc4",
    "SignalStrength": -66
  },
  {
    "index": 7,
    "SSID": "Vodafone V00002",
    "MAC": "00:00:00:00:01:02",
    "Type": "",
    "SignalStrength": -67
  },
  {
    "index": 8,
    "SSID": "Vodafone V00003",
    "MAC": "00:00:00:00:01:03",
    "Type": "OpenEnc1",
    "SignalStrength": -68
  }
];