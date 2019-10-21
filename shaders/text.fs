#version 120

uniform sampler2D sampler;

varying vec2 tex_coords;



void main(){
	
	
	
	//vec2 l = gl_FragCoord.xy/2000;
	
	gl_FragColor = texture2D(sampler,tex_coords);
}


