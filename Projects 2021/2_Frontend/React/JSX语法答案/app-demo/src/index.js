// 导入React相关包
import React from 'react'
import ReactDOM from 'react-dom'
// 定义 jsx元素所需数据
const couserListData = [
    {
        id: 1200,
        couserName: '前端与移动开发'
    },
    {
        id: 1201,
        couserName: 'Java'
    },
    {
        id: 1202,
        couserName: 'Python'
    },
    {
        id: 1203,
        couserName: 'UI'
    }
]

// 创建jsx 元素
const couserList = (
    <div>
        <h1>欢迎来到博学谷学习</h1>
        <ul>
        {couserListData.map( item => 
            <li key={item.id}>
               
                <h2>{item.couserName} --课程ID：{ item.id }</h2>
            </li>
        )}
        
    </ul>
    </div>
)

// 渲染React元素
ReactDOM.render( couserList,document.getElementById('root'));