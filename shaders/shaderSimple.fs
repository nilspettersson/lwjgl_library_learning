#version 120

varying vec2 tex_coords;

uniform vec4 color;

uniform mat4 projection;

void main(){
	gl_FragColor=projection*vec4(color);
}