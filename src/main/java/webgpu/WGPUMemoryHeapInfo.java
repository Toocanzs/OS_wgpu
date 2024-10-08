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
public class WGPUMemoryHeapInfo extends StructObject {
	public WGPUMemoryHeapInfo() {
		super();
	}
	/// C type : WGPUHeapProperty
	@Field(0) 
	public long properties() {
		return this.io.getLongField(this, 0);
	}
	/// C type : WGPUHeapProperty
	@Field(0) 
	public WGPUMemoryHeapInfo properties(long properties) {
		this.io.setLongField(this, 0, properties);
		return this;
	}
	@Field(1) 
	public long size() {
		return this.io.getLongField(this, 1);
	}
	@Field(1) 
	public WGPUMemoryHeapInfo size(long size) {
		this.io.setLongField(this, 1, size);
		return this;
	}
	public WGPUMemoryHeapInfo(Pointer pointer) {
		super(pointer);
	}
}
