#version 150 core

uniform sampler2D Texture0;

in vec4 pass_Color;
in vec2 pass_TextureCoord;

out vec4 out_Color;

void main(void) {
	out_Color = pass_Color;
        out_Color = texture(Texture0, pass_TextureCoord);
}