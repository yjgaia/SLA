function YOGIO(o) {
	var YOGIO_URL = 'http://yog.io';
	
	var url = location.href;
	
	if (o !== undefined) {
		if (o.url !== undefined) {
			url = o.url;
		}
	}
	
	document.write('<a href="' + YOGIO_URL + '/func/share?url=' + url + '"><img style="border: none;" src="' + YOGIO_URL + '/img/button/fb.png"></a>');
}