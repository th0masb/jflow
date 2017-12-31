/**
 * Copyright � 2017 Lhasa Limited
 * File created: 6 Nov 2017 by ThomasB
 * Creator : ThomasB
 * Version : $Id$
 */
package io.xyz.common.splines2D;

/**
 * @author ThomasB
 * @since 6 Nov 2017
 */
public class EdgeVisualPolicy // implements IDataStreamSerializable
{
	// /**
	// * The policy mapping
	// */
	// private final Map<Class<? extends IPolyEdge>, SplineType> policies = new
	// HashMap<>();
	//
	//
	// public EdgeVisualPolicy()
	// {
	// }
	//
	// public void putPolicy(final Class<? extends IPolyEdge> edgeClazz, final
	// SplineType policy)
	// {
	// policies.put(edgeClazz, policy);
	// }
	//
	// /**
	// *
	// */
	// public EdgeVisualPolicy(final Map<Class<? extends IPolyEdge>, SplineType>
	// policies)
	// {
	// this.policies.putAll(policies);
	// }
	//
	// public void assignEdgeVisualPolicy(final IGraph<?, ? extends IPolyEdge>
	// graph)
	// {
	// for (final IPolyEdge edge : graph.getEdges())
	// {
	// if (edge.getFrom().getPoint3D() == null ||
	// edge.getTo().getPoint3D() == null ||
	// edge.getLayoutPoints() == null)
	// {
	// throw new IllegalArgumentException("Knot points incorrectly assigned.");
	// }
	//
	// if (!policies.keySet().contains(edge.getClass()))
	// {
	// throw new IllegalArgumentException("No policy for this edge class: " +
	// edge.getClass().getSimpleName());
	// }
	//
	// edge.setDrawingPolicy(policies.get(edge.getClass()));
	// }
	// }
	//
	// //------------------------------------------------------------------------------------
	// /*
	// * Serialization stuff
	// */
	// private static final String CLAZZ_ARRAY_ID = "Class entry array";
	// private static final String DRAW_POLICY_ARRAY_ID = "Draw policy array";
	//
	//
	// @SuppressWarnings("unchecked")
	// @Override
	// public void fromDataStream(final IDataStreamReader reader) throws
	// DataStreamSerializationException
	// {
	// try
	// {
	// // try
	// // {
	// final String[] clazzArr = reader.readStringArray(), splineArr =
	// reader.readStringArray();
	//
	// for (int i = 0; i < clazzArr.length; i++)
	// {
	// putPolicy((Class<? extends IPolyEdge>) Class.forName(clazzArr[i]),
	// SplineType.valueOf(splineArr[i]));
	// }
	// // }
	// // catch (final IOException ioe)
	// // {
	// // ioe.printStackTrace();
	// // assert policies != null;
	// // policies.clear();
	// // }
	// }
	// catch (final Exception e)
	// {
	// throw new DataStreamSerializationException(e);
	// }
	// }
	//
	// @Override
	// public void toDataStream(final IDataStreamWriter writer) throws
	// DataStreamSerializationException
	// {
	// try
	// {
	// final List<Map.Entry<Class<? extends IPolyEdge>, SplineType>> policies = new
	// ArrayList<>(this.policies.entrySet());
	// final int npolicy = policies.size();
	// final String[] clazzArr = new String[npolicy], splineArr = new
	// String[npolicy];
	//
	// for (int i = 0; i < npolicy; i++)
	// {
	// clazzArr[i] = policies.get(i).getKey().getCanonicalName();
	// splineArr[i] = policies.get(i).getValue().name();
	// }
	//
	// writer.writeStrings(CLAZZ_ARRAY_ID, clazzArr);
	// writer.writeStrings(DRAW_POLICY_ARRAY_ID, splineArr);
	// }
	// catch (final Exception e)
	// {
	// throw new DataStreamSerializationException(e);
	// }
	// }
}

/*
 * ---------------------------------------------------------------------* This
 * software is the confidential and proprietary information of Lhasa Limited
 * Granary Wharf House, 2 Canal Wharf, Leeds, LS11 5PS --- No part of this
 * confidential information shall be disclosed and it shall be used only in
 * accordance with the terms of a written license agreement entered into by
 * holder of the information with LHASA Ltd.
 * ---------------------------------------------------------------------
 */