$(document).ready(function()
{
    var node=[];
    var linkmes=[];//连接的信息

    getSyncRequest(
        '/authorGraph',
        function (res) {
            console.log(res);

            // var nodeTmp = {
            //     name: '校园大数据',
            //     draggable: true,
            //     category: 0,
            // };
            //node.push(nodeTmp);

            //var list = res;
            for(let i=0;i<res.length;i++){
                //nodeTmp.name=res[i].name;
                node.push({
                    name:res[i].name,
                    draggable:true,
                    category:0,
                    symbolSize:res[i].heat/80
                });
               // console.log(res[i].name);
            }
            console.log(node);

            // var relation={
            //     source:"1",
            //     target:"2"
            // };
            for(let i=0;i<res.length;i++){
                //relation.source=res[i].name;
                if(res[i].relatedAuthors!=null){
                    for(let m=0;m<res[i].relatedAuthors.length;m++){
                        //console.log(res[i].relatedAuthors.length);
                        //relation.target=res[i].relatedAuthors[m];
                        linkmes.push({
                            source:res[i].name,
                            target:res[i].relatedAuthors[m]
                        });
                    }
                }
            }
            console.log(linkmes);
        },
        function(error){
            alert(error);
        }
    )
    var myChart = echarts.init(document.getElementById('main'));

    var dataArr = [
        {name: "A. ?ut卯i",

            draggable: true,
            category: 0
        },
        {name: 'A. A. Philip',
            draggable: true,
            category: 0
        },
        {name: 'A. A. Sawant',

            draggable: true,
            category: 0
        },
    ];
    option = {
        backgroundColor: '#1a4377',

        tooltip: {},
        animationDurationUpdate: 1500,
        animationEasingUpdate: 'quinticInOut',
        color:['#83e0ff','#45f5ce','#b158ff'],
        series:
            [
    {
        type: 'graph',
        focusNodeAdjacency: true,
        itemStyle: {
            normal: {
                borderColor: '#04f2a7',
                borderWidth: 2,
                shadowBlur: 20,
                shadowColor: '#04f2a7',
                color: '#001c43',
            }
        },
        //symbolSize: 30,
        layout: 'force',
        force:{
        repulsion:1000,
        edgeLength:50
    },
        roam: true,
        label: {
        normal: {
        show: true
    }
    },
        data: node,
        //data:dataArr,
        links:linkmes,
        lineStyle: {
        normal: {
        opacity: 0.9,
        width: 2,
        curveness: 0    //线的弯曲度
    }
    },
        categories:[
    {name: '0'},
    {name: '1'},
    {name: '2'}
        ]
    }
        ]
    }
        console.log(dataArr);
        myChart.setOption(option);




}
)