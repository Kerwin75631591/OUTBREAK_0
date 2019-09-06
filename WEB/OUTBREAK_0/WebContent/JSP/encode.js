function encode(string){
	var enc="";
	//alert(string.length);
	for(var i=0;i<string.length;i++){
		switch(i%3){
		case 0:
			enc+=String.fromCharCode(string.charCodeAt(i)+1);
			break;
		case 1:
			enc+=String.fromCharCode(string.charCodeAt(i)+2);
			break;
		case 2:
			enc+=String.fromCharCode(string.charCodeAt(i)+3);
			break;
		}
	}
	return enc;
}