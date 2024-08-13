package webgpu;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
/**
 * <i>native declaration : webgpu.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("webgpu") 
public class WGPUSurfaceDescriptorFromXcbWindow extends StructObject {
	public WGPUSurfaceDescriptorFromXcbWindow() {
		super();
	}
	/// C type : WGPUChainedStruct
	@Field(0) 
	public WGPUChainedStruct chain() {
		return this.io.getNativeObjectField(this, 0);
	}
	/// C type : WGPUChainedStruct
	@Field(0) 
	public WGPUSurfaceDescriptorFromXcbWindow chain(WGPUChainedStruct chain) {
		this.io.setNativeObjectField(this, 0, chain);
		return this;
	}
	/// C type : void*
	@Field(1) 
	public Pointer<? > connection() {
		return this.io.getPointerField(this, 1);
	}
	/// C type : void*
	@Field(1) 
	public WGPUSurfaceDescriptorFromXcbWindow connection(Pointer<? > connection) {
		this.io.setPointerField(this, 1, connection);
		return this;
	}
	@Field(2) 
	public int window() {
		return this.io.getIntField(this, 2);
	}
	@Field(2) 
	public WGPUSurfaceDescriptorFromXcbWindow window(int window) {
		this.io.setIntField(this, 2, window);
		return this;
	}
	public WGPUSurfaceDescriptorFromXcbWindow(Pointer pointer) {
		super(pointer);
	}
}