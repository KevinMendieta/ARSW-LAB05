//@author hcadavid

apimock=(function(){

	var mockdata=[];

	mockdata["johnconnor"]=	[{author:"johnconnor","points":[{"x":150,"y":120},{"x":215,"y":115}],"name":"house"},
	 {author:"johnconnor","points":[{"x":340,"y":240},{"x":15,"y":215}],"name":"gear"}];
	mockdata["maryweyland"]=[{author:"maryweyland","points":[{"x":140,"y":140},{"x":115,"y":115}],"name":"house2"},
	 {author:"maryweyland","points":[{"x":140,"y":140},{"x":115,"y":115}],"name":"gear2"}];
	 mockdata["max"] = [{author:"max", "points":[{"x":50, "y":50}, {"x":50, "y":100}, {"x":100, "y":100}, {"x":100, "y":50}, {"x":50, "y":50}], "name":"Square"}, {author:"max", "points":[{"x":100, "y":100}, {"x":50, "y":150}, {"x":150, "y":150}, {"x":100, "y":100}], "name":"Triangle"}, {author:"max", "points":[{"x":340,"y":240},{"x":15,"y":215}, {"x":50,"y":150}, {"x":340,"y":240}], "name":"LUL"}]
	 mockdata["carlgrimes"] =[{author:"carlgrimes", "points":[{"x":150,"y":120},{"x":150,"y":120}], "name":"kppa"}, {author:"carlgrimes", "points":[{"x":150,"y":120},{"x":150,"y":120}], "name":"Kappita"}, {author:"carlgrimes", "points":[{"x":150,"y":120}], "name":"Kappiat"}]


	return {
		getBlueprintsByAuthor:function(authname,callback){
			callback(
				mockdata[authname]
			);
		},

		getBlueprintsByNameAndAuthor:function(authname,bpname,callback){

			callback(
				mockdata[authname].find(function(e){return e.name===bpname})
			);
		}
	}

})();

apiclient = (function(){
	return {
		getBlueprintsByAuthor:function(authname,callback){
			console.log(authname);
			$.get("/blueprints/" + authname, callback);
		},

		getBlueprintsByNameAndAuthor:function(authname,bpname,callback){
			$.get("/blueprints/" + authname + "/" + bpname, callback);
		}
	};
})();

/*
Example of use:
var fun=function(list){
	console.info(list);
}

apimock.getBlueprintsByAuthor("johnconnor",fun);
apimock.getBlueprintsByNameAndAuthor("johnconnor","house",fun);*/
