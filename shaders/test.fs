#version 120

varying vec2 tex_coords;

uniform vec4 color;


void main(){
	vec2 l = gl_FragCoord.xy/1080;
	
	gl_FragColor=vec4(l.x,l.y,1,1);
}