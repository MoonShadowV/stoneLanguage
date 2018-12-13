package stone.chap6;

//用于记录变量名称与值的对应关系的数据结构
public interface Environment {
    void put(String name, Object value);
    Object get(String name);
}
