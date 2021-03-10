package nodes;

import exceptions.ChildNodeException;
import propertys.DataProperty;
import propertys.ObjectProperty;
import propertys.Property;

/**
 * Главный узел графовой структуры - служит корнем графа
 * и/или родителем для узлов типа IndividualNode
 *
 * @see IndividualNode
 * @see ObjectProperty
 * */
public class ClassNode extends Node
{
    private int individualCount = 0;    //счетчик индивидов для которых данный
                                        //узе является родительским

    private String state;               //Поле отвечающее за состояния данного узла

    /**
     * Конструктор с параметрами.
     * Поскольку нет родителя, то узел
     * приобритает состояние класс
     *
     * @param name   - имя данного узла
     * */
    public ClassNode(String name)
    {
        this.data = name;
        this.state = "Класс";
    }

    /**
     * Медот для добавления дочернего узла
     * Формирует связь - свойство объекта
     *
     * @param node                - дочерний узел
     * @throws ChildNodeException - исключение созданий связей
     *                              между узлами в графовой структуре
     * */
    public void addChild (Node node) throws ChildNodeException
    {
        Property property;

        //Если дочерний узел не принадлежит типу ClassNode или IndividualNode,
        //то выбрасываем исключение связей узлов
        if ((!(node instanceof ClassNode)) && (!(node instanceof IndividualNode)))
        {
            throw new ChildNodeException("Для узла типа" + this.getClass() + " дочерним узлом могут быть" +
                    " узлы: " + ClassNode.class + " или " + IndividualNode.class);
        }

        //Если дочерний узел принадлжеит классу IndividualNode
        if (node instanceof IndividualNode)
        {
            //Установка родителя при помощи метода setParent класса
            //IndividualNode, где конкретно указано какой тип
            //Родителя может быть.
            //при использовании общего метода setParent последюущий метод
            //addID бросает исключение
            node.setParent(this);

            //Увеличиваем счетчик индивидов для данного узла
            individualCount++;

            //Для узла индивида автоматически добавляем поле ID
            ((IndividualNode) node).addID();

            //Если в состоянии узла еще не указано, что он имеет
            //индивида к текущему состоянию приписываем:
            //имеет индивида
            if (!this.state.contains(", имеет индивида"))
            {
                this.state = this.state + ", имеет индивида";
            }

            //Создание св-ва данных
            property = new DataProperty(node);
        }
        //Иначе если тип переданного узла ClassNode то у него устанавливается
        //статус Подкласс
        else {
            node.setParent(this);
            ((ClassNode) node).state = "Подкласс";

            //Если у текущего узла не указано в состоянии, что он имеет подкласс
            //то дописываем это
            if (!this.state.contains(", имеет подкласс"))
            {
                this.state = this.state + ", имеет подкласс";
            }

            //создание объектного свойства
            property = new ObjectProperty(node);
        }


        propertyList.add(property);
    }

    /**
     * Метод для получения счетчика индивидов данного класса
     *
     * @return значение счетчика индивидов
     * */
    public int getIndividualCount()
    {
        return this.individualCount;
    }


    public String getState()
    {
        return state;
    }

    @Override
    public String toString() {
        return "ClassNode{" +
                "parent=" + parent +
                ", individualCount=" + individualCount +
                ", state='" + state + '\'' +
                ", name='" + data + '\'' +
                ", propertyList=" + propertyList +
                '}';
    }
}
