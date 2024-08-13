package webgpu;
import org.bridj.IntValuedEnum;
import org.bridj.Pointer;
import org.bridj.StructObject;
import org.bridj.ann.Field;
import org.bridj.ann.Library;
import webgpu.WebgpuLibrary.WGPUSharedFenceType;
/**
 * <i>native declaration : webgpu.h</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> or <a href="http://bridj.googlecode.com/">BridJ</a> .
 */
@Library("webgpu") 
public class WGPUSharedFenceExportInfo extends StructObject {
	public WGPUSharedFenceExportInfo() {
		super();
	}
	/// C type : WGPUChainedStructOut*
	@Field(0) 
	public Pointer<WGPUChainedStructOut > nextInChain() {
		return this.io.getPointerField(this, 0);
	}
	/// C type : WGPUChainedStructOut*
	@Field(0) 
	public WGPUSharedFenceExportInfo nextInChain(Pointer<WGPUChainedStructOut > nextInChain) {
		this.io.setPointerField(this, 0, nextInChain);
		return this;
	}
	/// C type : WGPUSharedFenceType
	@Field(1) 
	public IntValuedEnum<WGPUSharedFenceType > type() {
		return this.io.getEnumField(this, 1);
	}
	/// C type : WGPUSharedFenceType
	@Field(1) 
	public WGPUSharedFenceExportInfo type(IntValuedEnum<WGPUSharedFenceType > type) {
		this.io.setEnumField(this, 1, type);
		return this;
	}
	public WGPUSharedFenceExportInfo(Pointer pointer) {
		super(pointer);
	}
}