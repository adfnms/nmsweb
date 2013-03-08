function getVersion() {
	var url = location.href;
	url = url.split('//');
	url = url[1].split('/')[1];
	return url;
}
