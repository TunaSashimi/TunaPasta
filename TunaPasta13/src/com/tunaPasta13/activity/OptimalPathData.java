package com.tunaPasta13.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OptimalPathData {
//使用Dijkstra算法
	private String[] nodeName0 = {
			"AB宿舍","CD宿舍","菜市场/网络中心","第二/三食堂"," DG座教室",
			"大礼堂","东门","第四食堂","EF宿舍","E座教室",
			"附中附小","G座宿舍","荷花池","互助学习中心","教师公寓",
			"旧行政楼","科学楼","路口1","路口2","路口3",
			"路口4","路口5","路口6","路口7","路口8",
			"路口9","路口10","路口11","路口12","路口13",
			"路口14","理科楼","留学生宿舍","溜冰场",
			"篮球场","789号楼","路口15","路口16","体育馆",
			"田径场","网球场","校门","学术交流中心","校训碑",
			"新行政中心","校医院","邮局","研究生宿舍","艺术教育中心",
			"艺术学院","游泳池","至诚书院","图书馆","水库"
			}; // 各地点的名字
	private int[][] nodeId0 = {
			{1,8,9},{0,21},{10,13},{16,21},{24,25,28},
			{15,43,44},{23},{11,51},{0,36,38},{23,37},
			{2},{7,29},{14,43},{2,46},{12,22,26},
			{5,25,31,43},{3,31},{36,47,49},{36,50},{0,39},
			{35,39},{1,3,34,44},{14,33,35,44},{6,9,51},{4,31},
			{4,15,43},{14,27,37,46},{26,37,41},{4,29},{11,28},
			{32,51},{15,16,24,47},{30},{22,34},{21,33,40},
			{20,22,45,50},{8,17,18,38,53},{9,26,27,43,52},{8,36},{19,20},
			{34},{27},{49},{5,12,15,25,37},{5,21,22},
			{35,48},{13,26},{17,31},{45},{17,42},
			{18,35},{7,23,30},{37},{36}
			}; // 一维：当前点；二维：该点相邻点的id
	private int[][] nodeDistance0 = {{53,73,113},{53,48},{155,63},{108,45},{36,101,98},
			{79,88,160},{54},{55,56},{73,58,16},{180,119},{155},{55,48},{73,189},{63,35},
			{73,121,164},{79,88,137,80},{108,147},{64,229,96},{147,40},{113,75},{27,57},
			{48,45,91,118},{121,45,164,190},{54,180,48},{36,157},{101,88,93},{164,107,134,29},
			{107,132,50},{98,30},{48,30},{50,66},{137,147,157,301},{50},{45,59},{91,59,49},
			{27,164,76,121},{58,64,147,53,34},{119,134,132,43,107},{16,53},{75,57},{49},{50},
			{43},{88,189,80,93,43},{160,118,190},
			{76,46},{35,29},{229,301},{46},{96,43},{40,121},{56,48,66},{107},{34}};// 一维：当前点；二维：该点相邻点的距离
	
	private String[] nodeName;
	private int[][] nodeId;
	private int[][] nodeDistance;
	
	private String[] nodeName1 = {
			"AB宿舍","CD宿舍","菜市场/网络中心","第二/三食堂"," DG座教室",
			"大礼堂","东门","第四食堂","EF宿舍","E座教室",
			"附中附小","G座宿舍","荷花池","互助学习中心","教师公寓",
			"旧行政楼","科学楼","路口1","路口2","路口3",
			"路口4","路口5","路口6","路口7","路口8",
			"路口9","路口10","路口11","路口12","路口13",
			"路口14","理科楼","留学生宿舍","溜冰场",
			"篮球场","789号楼","路口15","路口16","体育馆",
			"田径场","网球场","校门","学术交流中心","校训碑",
			"新行政中心","校医院","邮局","研究生宿舍","艺术教育中心",
			"艺术学院","游泳池","至诚书院","图书馆","水库"
			};
// 各地点的名字
	private int[][] nodeId1 = {
			{1,8},{0,21},{10,13},{16,21},{24,25,28},{15,43,44},
			{23},{11,51},{0,36,38},{23,37},{2},{7,29},
			{43},{2,46},{22,26},{5,25},{3,31},{36,47,49},
			{36,50},{35},{1,3},{14,35,44},{6,9,51},{4,31},
			{4,15,43},{14,27,37,46},{26,37,41},{4,29},{11,28},
			{32,51},{16,24,47},{30},{20,22,45,50},{8,17,18,38},
			{9,26,27,43,52},{8,36},{27},{49},{5,12,25,37},
			{5,21,22},{35,48},{13,26},{17,31},{45},
			{17,42},{18,35},{7,23,30},{37}

			}; // 一维：当前点；二维：该点相邻点的id
	private int[][] nodeDistance1 = {{53,73},{53,48},{155,63},{108,45},{36,101,98},
			{79,88,160},{54},{55,56},{73,58,16},{180,119},{155},{55,48},{189},
			{63,35},{121,164},{79,88},{108,147},{64,229,96},{147,40},{126},
			{168,224},{255,205,118},{701,474,669},{219,396},{36,167,213},
			{333,238,116,277},{0,134,133},{333,364},{60,0},{131,105},
			{575,287,742},{443},{835,724,937,955},{256,344,290,250},
			{483,338,441,354,484},{199,215},{703},{745},{455,392,587,573},
			{412,172,191},{197,309},{144,133},{137,638},{568},{369,507},{123,76},{431,438,532},{501}};
 // 一维：当前点；二维：该点相邻点的距离
	
	private int id; // 起始点对应的id
	private String str;

	private Set<Node> open = new HashSet<Node>(); // open用于存储未遍历的节点
	private Set<Node> close = new HashSet<Node>(); // close用来存储已遍历的节点
	private Map<String, Integer> path = new HashMap<String, Integer>();// 封装路径距离
	private Map<String, String> pathInfo = new HashMap<String, String>();// 封装路径信息
	public OptimalPathData(String str){
		this.str=str;
		if(str.equals("步行")){
			nodeName=nodeName0;
			nodeId=nodeId0;
			nodeDistance=nodeDistance0;
		}else{
			nodeName=nodeName1;
			nodeId=nodeId1;
			nodeDistance=nodeDistance1;
		}
	}
	public Node init(String startName) {
		Node startNode = new Node(startName);
		// 找到该起始点的id
		for (int i = 0; i < nodeName.length; i++) {
			if (nodeName[i].equals(startName)) {
				id = i;
				break;
			}
		}

		// 初始化直接相连的路径
		String[] nodeNameTemp = new String[nodeId[id].length+1];
		int t;
		for (t = 0; t < nodeId[id].length; t++) {
			path.put(nodeName[nodeId[id][t]], nodeDistance[id][t]);
			pathInfo.put(nodeName[nodeId[id][t]], startName + "-" + nodeName[nodeId[id][t]]);
			nodeNameTemp[t] = nodeName[nodeId[id][t]];
		}
		 nodeNameTemp[t] = startName;
		// 初始化不相连的路径
		String[] nodeNone = subtract(nodeName, nodeNameTemp);
		for (int i = 0; i < nodeNone.length; i++) {
			path.put(nodeNone[i], Integer.MAX_VALUE);
			pathInfo.put(nodeNone[i], startName);
		}
		// 将初始节点放入close,其他节点放入open
		Node start = new RoadMapBuilder(startNode,str).build(open, close);
		return start;
	}

	// 字符数组相减
	public String[] subtract(String[] bigStr, String[] smallStr) {
		ArrayList<String> rsList = new ArrayList();
		boolean flag = true;
		for (int i = 0; i < bigStr.length; i++) {
			for (int j = 0; j < smallStr.length; j++) {
				if (bigStr[i].equals(smallStr[j])) {
					flag = false;
					break;
				}
			}
			if (flag) {
				rsList.add(bigStr[i]);
			}
			flag = true;
		}
		String[] rsArr = new String[rsList.size()];
		for (int k = 0; k < rsList.size(); k++) {
			rsArr[k] = rsList.get(k);
		}
		return rsArr;
	}

	public void computePath(Node start) {
		Node nearest = getShortestPath(start);// 取距离start节点最近的子节点,放入close
		if (nearest == null) {
			return;
		}
		close.add(nearest);
		open.remove(nearest);
		Map<Node, Integer> childs = nearest.getChild();
		for (Node child : childs.keySet()) {
			if (open.contains(child)) {// 如果子节点在open中
				Integer newCompute = path.get(nearest.getName()) + childs.get(child);
				if (path.get(child.getName()) > newCompute) {// 之前设置的距离大于新计算出来的距离
					path.put(child.getName(), newCompute);
					
					start.getChild().put(child, newCompute);
					close.add(start);
					
					pathInfo.put(child.getName(), pathInfo.get(nearest.getName()) + "-" + child.getName());
				}
			}
		}
		computePath(start);// 重复执行自己,确保所有子节点被遍历
		computePath(nearest);// 向外一层层递归,直至所有顶点被遍历
	}

	// 返回路径顺序
	public String printPathInfo(String endName) {

		Set<Map.Entry<String, String>> pathInfos = pathInfo.entrySet();
		for (Map.Entry<String, String> pathInfo : pathInfos) {
			 System.out.println(pathInfo.getKey() + ":" +pathInfo.getValue());
			String strName = pathInfo.getValue();
			int lastId = strName.lastIndexOf("-");
			String strTemp = strName.substring((lastId + 1), strName.length());
			if (strTemp.equals(endName)) // 判断路径的终点和所指定的终点是否相同
				return strName;
		}
		return "";
	}

	/**
	 * 获取与node最近的子节点
	 */
	private Node getShortestPath(Node node) {
		Node res = null;
		int minDis = Integer.MAX_VALUE;
		Map<Node, Integer> childs = node.getChild();

		// 循环比较，找出离node最近的子节点
		for (Node child : childs.keySet()) {
			if (open.contains(child)) {
				int distance = childs.get(child);
				if (distance < minDis) {
					minDis = distance;
					res = child;
				}
			}
		}
		return res;
	}
}