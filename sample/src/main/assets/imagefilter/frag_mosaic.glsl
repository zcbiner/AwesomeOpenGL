precision highp float;
// 纹理坐标
varying vec2 v_textureCoordinate;
// 纹理采样器
uniform sampler2D u_texture;
// 纹理图片Size
const vec2 textureSize = vec2(500.0, 800.0);
// 马赛克Size
const vec2 mosaicSize = vec2(10.0, 10.0);

void main () {
    vec2 intXY = vec2(v_textureCoordinate.x * textureSize.x, v_textureCoordinate.y * textureSize.y);
    vec2 XYMosaic = vec2(floor(intXY.x/mosaicSize.x) * mosaicSize.x, floor(intXY.y/mosaicSize.y) * mosaicSize.y);
    vec2 UVMosaic = vec2(XYMosaic.x/textureSize.x, XYMosaic.y/textureSize.y);
    vec4 color = texture2D(u_texture, UVMosaic);
    gl_FragColor = color;
}