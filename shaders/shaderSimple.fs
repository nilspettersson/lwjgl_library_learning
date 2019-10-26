#version 120

varying vec2 tex_coords;

uniform vec4 color;


void main(){
	gl_FragColor=vec4(color);
}