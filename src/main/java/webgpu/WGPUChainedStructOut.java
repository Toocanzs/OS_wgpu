package webgpu;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import webgpu.WebgpuLibrary.WGPUSType;
/**
 * <i>native declaration : webgpu.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("webgpu") 
public class WGPUChainedStructOut extends StructObject {
	public WGPUChainedStructOut() {
		super();
	}
	/// C type : WGPUChainedStructOut*
	@Field(0) 
	public Pointer<WGPUChainedStructOut > next() {
		return this.io.getPointerField(this, 0);
	}
	/// C type : WGPUChainedStructOut*
	@Field(0) 
	public WGPUChainedStructOut next(Pointer<WGPUChainedStructOut > next) {
		this.io.setPointerField(this, 0, next);
		return this;
	}
	/// C type : WGPUSType
	@Field(1) 
	public IntValuedEnum<WGPUSType > sType() {
		return this.io.getEnumField(this, 1);
	}
	/// C type : WGPUSType
	@Field(1) 
	public WGPUChainedStructOut sType(IntValuedEnum<WGPUSType > sType) {
		this.io.setEnumField(this, 1, sType);
		return this;
	}
	public WGPUChainedStructOut(Pointer pointer) {
		super(pointer);
	}
}