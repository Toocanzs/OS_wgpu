package webgpu;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import webgpu.WebgpuLibrary.WGPUTexture;
import webgpu.WebgpuLibrary.WGPUTextureAspect;
/**
 * <i>native declaration : webgpu.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("webgpu") 
public class WGPUImageCopyTexture extends StructObject {
	public WGPUImageCopyTexture() {
		super();
	}
	/// C type : WGPUTexture
	@Field(0) 
	public WGPUTexture texture() {
		return this.io.getTypedPointerField(this, 0);
	}
	/// C type : WGPUTexture
	@Field(0) 
	public WGPUImageCopyTexture texture(WGPUTexture texture) {
		this.io.setPointerField(this, 0, texture);
		return this;
	}
	@Field(1) 
	public int mipLevel() {
		return this.io.getIntField(this, 1);
	}
	@Field(1) 
	public WGPUImageCopyTexture mipLevel(int mipLevel) {
		this.io.setIntField(this, 1, mipLevel);
		return this;
	}
	/// C type : WGPUOrigin3D
	@Field(2) 
	public WGPUOrigin3D origin() {
		return this.io.getNativeObjectField(this, 2);
	}
	/// C type : WGPUOrigin3D
	@Field(2) 
	public WGPUImageCopyTexture origin(WGPUOrigin3D origin) {
		this.io.setNativeObjectField(this, 2, origin);
		return this;
	}
	/// C type : WGPUTextureAspect
	@Field(3) 
	public IntValuedEnum<WGPUTextureAspect > aspect() {
		return this.io.getEnumField(this, 3);
	}
	/// C type : WGPUTextureAspect
	@Field(3) 
	public WGPUImageCopyTexture aspect(IntValuedEnum<WGPUTextureAspect > aspect) {
		this.io.setEnumField(this, 3, aspect);
		return this;
	}
	public WGPUImageCopyTexture(Pointer pointer) {
		super(pointer);
	}
}