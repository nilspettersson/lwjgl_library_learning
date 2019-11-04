#version 120

uniform sampler2D sampler;

varying vec2 tex_coords;

uniform vec4 color;


void main(){
	vec4 texture=texture2D(sampler,tex_coords);
	gl_FragColor=color+texture;
}