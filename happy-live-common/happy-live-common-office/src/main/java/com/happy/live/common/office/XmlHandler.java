
package com.happy.live.common.office;

import com.happy.live.common.base.StringHandler;
import org.dom4j.*;

import java.util.*;

/**
 * 此类描述的是: xml工具助手
 * @author: cuixinfu@ralncy.com
 * @date:2017年11月21日 下午1:28:59
 */
public class XmlHandler {

	/**
	 * 方法: mapToXML <br>
	 * @param params
	 *            map类型的转换参数
	 * @param isSort
	 *            转换参数是否进行排序
	 * @param sortType
	 *            排序方式(sort:从小到大；reverse：从大到小)
	 * @return
	 */
	public static String mapToXML(Map<String, Object> params, boolean isSort, String sortType) {
		//sign需要放到xml的最后
		ArrayList<String> keys = new ArrayList<String>(params.size());
		keys.addAll(params.keySet());
		//是否需要排序
		if (isSort) {
			if ("sort".equals(sortType)) {
				//参数名ASCII码从小到大排序（字典序）；
				Collections.sort(keys);
			} else {
				//参数名ASCII码从大到小排序（字典序）；
				Collections.sort(keys, Collections.reverseOrder());
			}
		}
		StringBuilder xml = new StringBuilder();
		xml.append("<xml>");
		for (int i = 0; i < keys.size(); i++) {
			xml.append("<").append(keys.get(i)).append(">");
			xml.append(StringHandler.getString(params.get(keys.get(i)), ""));
			xml.append("</").append(keys.get(i)).append(">");
		}
		xml.append("</xml>");
		return xml.toString();
	}


	/**
	 * 遍历当前节点元素下面的所有(元素的)子节点
	 * 
	 * @param node
	 */
	@SuppressWarnings("unchecked")
	private static void listNodes(Element node, Map<String, Object> map) {
		Map<String, Object> mapValue = new HashMap<String, Object>();
		map.put(node.getName(), mapValue);
		// 获取当前节点的所有属性节点
		List<Attribute> list = node.attributes();
		// 遍历属性节点
		Map<String, Object> attribute = new HashMap<String, Object>();
		for (Attribute attr : list) {
			attribute.put(attr.getName(), attr.getValue());
		}
		mapValue.put("attribute", attribute);
		if (!(node.getTextTrim().equals(""))) {
			mapValue.put("text", node.getText());
		}
		List<Map<String, Object>> element = new ArrayList<Map<String, Object>>();
		mapValue.put("element", element);
		// 当前节点下面子节点迭代器
		Iterator<Element> it = node.elementIterator();
		// 遍历
		while (it.hasNext()) {
			Map<String, Object> elementMap = new HashMap<String, Object>();
			element.add(elementMap);
			// 获取某个子节点对象
			Element e = it.next();
			// 对子节点进行遍历
			listNodes(e, elementMap);
		}
	}

	/**
	 * 设置所有节点
	 * 
	 * @param root
	 *            父节点
	 * @param map
	 */
	@SuppressWarnings("unchecked")
	private static void setNodes(Element root, Map<String, Object> map) {
		for (String key : map.keySet()) {
			Element element1 = root.addElement(key);
			Map<String, Object> Map1 = (Map<String, Object>) map.get(key);
			Map<String, Object> attribute = (Map<String, Object>) Map1.get("attribute");
			// 设置属性
			if (StringHandler.isNotEmpty(attribute)) {
				setAttribute(element1, attribute);
			}
			// 设置文本
			String text = StringHandler.getString(Map1.get("text"), "");
			if (StringHandler.isNotEmpty(text)) {
				element1.addText(text);
			}
			// 设置子节点
			List<Map<String, Object>> childElements = (List<Map<String, Object>>) Map1.get("element");
			for (Map<String, Object> map2 : childElements) {
				setNodes(element1, map2);
			}
			break;
		}
	}
	
	/**
	 * 设置属性
	 * 
	 * @param root
	 * @param map
	 */
	private static void setAttribute(Element root, Map<String, Object> map) {
		for (String key : map.keySet()) {
			root.addAttribute(key, StringHandler.getString(map.get(key), ""));
		}
	}
	
	/**
	 * 
	 * 将map 解析为Document 对象
	 *            父节点
	 * @param map
	 */
	@SuppressWarnings("unchecked")
	public static Document mapToXml(Map<String, Object> map) {
		Element root = null;
		Document document = null;
		for (String key : map.keySet()) {
			Map<String, Object> Map1 = (Map<String, Object>) map.get(key);
			root = DocumentHelper.createElement(key);
			document = DocumentHelper.createDocument(root);
			List<Map<String, Object>> childElements = (List<Map<String, Object>>) Map1.get("element");
			for (Map<String, Object> childMap : childElements) {
				setNodes(root, childMap);
			}
			break;
		}
		return document;
	}

	/**
	 * 将xml 解析为map
	 * @param xml
	 * @param map
	 */
	public static void xmlToMap(String xml, Map<String, Object> map) {
		Document document;
		try {
			document = DocumentHelper.parseText(xml);
			Element node = document.getRootElement();
			listNodes(node, map);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

}
