package org.xian;

/**
 * @author lgx
 */
public class Main {
    public static void main(String[] args) {
        String dataJson = """
                {
                    params: {
                        title: '1',
                        name: '23'
                    },
                    results: ['title', 'name']
                }
                """;
        String sql = SqlGenerater.createSelect(dataJson).sql();

        System.out.println(sql);
    }
}