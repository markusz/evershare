
	  function geocodeAddress(){
		
		  var geocoder = new google.maps.Geocoder();
		//var address2 = document.getElementById("address").value;

	  	var address = $('[name=loc]').val()
	  	
	  	//alert(address);
	  
	  	geocoder.geocode( { 'address': address}, function(results, status) {

	  			//alert("in f")
		  if (status == google.maps.GeocoderStatus.OK) {
		      var latitude = results[0].geometry.location.lat();
		      var longitude = results[0].geometry.location.lng();
		      
		      //alert(latitude + " - "+longitude)
		      
		      $('[name=lat]').attr("value", latitude);
		      $('[name=lng]').attr("value",longitude);
		      
		      } 
		  }); 
	  }