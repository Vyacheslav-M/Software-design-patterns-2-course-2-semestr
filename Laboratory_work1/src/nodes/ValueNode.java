package nodes;

/**
 * Узел значение
 * Фомрмируется при вывозе метод addAttribute
 * у узла типа IndividualNode
 *
 * Содержит значение Атрибута
 *
 * @see nodes.AttributeNode
 *
 * @author Vladislav Sapozhnikov 19-IVT-3
 * @author Valerii Sukhorukov    19-IVT-3
 * @author Vyacheslav Mostashov  19-IVT-3
 * */
public class ValueNode extends Node
{
    /**
     * Конструктор с параметрами
     *
     * @param data   - значение данного узла
     * @param parent - родитель данного узла
     * */
    public ValueNode(String data, Node parent)
    {
        this.parent = parent;
        this.data = data;
        this.propertyList = null;
    }
}
