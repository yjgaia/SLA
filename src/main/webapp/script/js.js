function YOGIO(o) {
	//var YOGIO_URL = 'http://yog.io';
	var YOGIO_URL = 'http://test.com:8080/SLA';
	
	var url = location.href;
	
	if (o !== undefined) {
		if (o.url !== undefined) {
			url = o.url;
		}
	}
	
	var chars = 'ABCDEFGHIJKLMNOPQRSTUVWXTZabcdefghiklmnopqrstuvwxyz';
	var randomString = '';
	for (var i = 0 ; i < chars.length ; i++) {
		var rnum = Math.floor(Math.random() * chars.length);
		randomString += chars.substring(rnum, rnum + 1);
	}
	
	document.write(
		'<form id="facebook-login-form-' + randomString + '" action="' + YOGIO_URL + '/func/signin/facebook" method="POST">'
			+ '<input type="hidden" name="scope" value="email,publish_stream,offline_access" />'
			+ '<input type="hidden" name="redirect_uri" value="' + YOGIO_URL + '/func/share?url=' + url + '" />'
		+ '</form>'
		+ '<a href="javascript:document.getElementById(\'facebook-login-form-' + randomString + '\').submit()">'
			+ '<img style="border: none;" src="' + YOGIO_URL + '/img/button/fb.png">'
		+ '</a>'
	);
}