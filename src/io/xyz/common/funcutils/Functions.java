/**
 * 
 */
package io.xyz.common.funcutils;

/**
 * @author t
 *
 */
public class Functions 
{
	/**
	 * Object mapped to object
	 * 
	 * @param <DomainType> -type of elements in the function domain
	 * @param <TargetType> -type of elements in the function target
	 */
	public static interface OMO<DomainType, TargetType> 
	{
		TargetType map(DomainType input);
	}
	
	/**
	 * int mapped to Object
	 * 
	 * @param <TargetType> -type of elements in the function target
	 */
	public static interface IMO<TargetType>
	{
		TargetType map(int n);
	}
	
	/**
	 * Object mapped to int
	 *
	 * @param <DomainType> -type of elements in the function domain
	 */
	public static interface OMI<DomainType> 
	{
		int map(DomainType input);
	}
	
	/**
	 * double mapped to Object
	 *
	 * @param <TargetType> -type of elements in the function target
	 */
	public static interface DMO<TargetType> 
	{
		TargetType map(double x);
	}
	
	/**
	 * Object mapped to double
	 *
	 * @param <DomainType>
	 */
	public static interface OMD<DomainType> 
	{
		double map(DomainType input);
	}
	
	/**
	 *	int mapped to double
	 */
	public static interface IMD
	{
		double map(int n);
	}
	
	/**
	 *	double mapped to int
	 */
	public static interface DMI
	{
		int map(double x);
	}
	
	/**
	 * double mapped to double 
	 *
	 */
	public static interface DMD
	{
		double map(double x);
	}
	
	public static interface IMI
	{
		int map(int n);
	}
}
