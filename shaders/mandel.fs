#version 120


varying vec2 tex_coords;



float map(float value, float min1, float max1, float min2, float max2){
	float perc = (value - min1) / (max1 - min1);

	// Do the same operation backwards with min2 and max2
	return perc * (max2 - min2) + min2;
}


void main(){
	
	
	
	vec2 l = gl_FragCoord.xy/1080;
	
	gl_FragColor = vec4(l.x,l.y,l.x+l.y,1);

}


