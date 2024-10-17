# 文件模式

```
open(文件的路程,模式)
模式: w: 可写
      r: 可读
 wirte是写
```

```python
#该函数用于打开文件test.txt，并以附加模式（'a'）打开文件，如果文件存在，则在文件末尾进行写入，如果文件不存在，则创建新文件并打开。
# 返回一个文件对象t，可以通过该对象对文件进行读写操作。
#t = open('test.txt', 'a')
#t.write('hello world  \n' *5)
#t.close()
#读数据

t = open('test.txt', 'r')
content=t.read()
print(content)
t.close()

```

# Urllib

## 概念

```
解释1：通过一个程序，根据url进行爬取网页,获取有用的信息
解释2：使用程序模拟浏览器，去向服务器发送请求，获取响应信息
```

## 爬虫核心

```
1:爬取网页：爬取整个网页，包含了网页中所有内容
2:解析数据:将网页中你得到的数据进行解析
3:难点:爬虫与反爬虫之间的博弈
```

## 爬虫的用途？

```
数据分析
社交软件冷启动
舆情监控
竞争对手监控
```

## 爬虫分类

```
聚焦爬虫
   功能:
     根据需求,实现爬虫程序
   设计思路:
     1:确定要爬的url
          如何获取url
     2:模拟浏览器通过http访问url，获取返回码
          如何访问
     3:解析html字符串
          如何解析
   
```

## 反爬手段

```
1:user-Agent
2:代理IP
3:验证码手段
4:动态加载网页
5:数据加密
```

## Urllib库使用

```
urllib.request.urlopen() 模拟浏览器向服务器发送请求
resonse 服务器返回的数据
  response的数据类型是HttpResponse
  字节->字符串
          解码decode
  字符串->字节
          编码encode
   read()字节读取二进制
   readline()读取一行
   readlines()一行一行读取直至结束
   getcode()获取状态码
   geturl()获取url
   getheaders()获取headers
   urllib.request.urlretrieve()
     请求网页
     请求图片
     请求视频
```

# 爬取

## 爬取百度

```python
# 使用Urllib来获取百度首页的源码
import urllib.request
# 定义一个Url
url = 'http://www.baidu.com/'
# 模拟浏览器向服务器发送请求 response响应
response = urllib.request.urlopen(url)
# 获取响应中的页面的源码
html = response.read().decode('utf-8')
# 打印响应的HTTP状态码
print(html)
```

## 一个类型和六个方法

```python
import urllib.request
# 定义一个Url
url = 'http://www.baidu.com/'
# 模拟浏览器向服务器发送请求 response响应
response = urllib.request.urlopen(url)
# 一个类型和六个方法
# response是HTTPResponse类型
# print(type(response))
# 按照一字节一字节去读效率低
# content = response.read()
# content = response.read(5)
# print(content)
# 读取一行
# content =  response.readline()
# 全部读完
# response.readlines()
# 获取状态码
print(response.getcode())
# 获取地址
print(response.geturl())
# 获取头部信息
print(response.getheaders())
```

## 下载

```python
import urllib.request
# 下载网页
url_page = 'http://www.baidu.com'
urllib.request.urlretrieve(url_page, 'baidu.html')
# 下载图片
url_img = 'https://img.zcool.cn/community/01a88459ae0d88a801211d250cb394.jpg@1280w_1l_2o_100sh.jpg'
urllib.request.urlretrieve(url_img, 'img.jpg')

# 下载视频
url_video = 'https://v6.huanqiucdn.cn/4394989evodtranscq1500012236/b573d2971253642700713316438/v.f100830.mp4'
urllib.request.urlretrieve(url_video, 'video.mp4')
```

## 请求对象的定制

```python
UA介绍: User Agent中文名为用户代理.简称UA。
import urllib.request
url = 'https://www.baidu.com'
# url的组成
# http/https      www.baidu.com
# 协议               主机             端口号
# http 80
# https 443
# Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0

headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0'
}
# 因为Urlopen方法中不能存储字典
# 请求对象的定制
# 因为参数顺序问题不难直接写Url和headers 中间还有个data 我们需要关键字传参
url = urllib.request.Request(url,headers=headers)
response=urllib.request.urlopen(url)
contnet = response.read().decode('utf-8')
print(contnet)
```

## 编解码

###   get请求方式

```python
urllib.parse.quote()
import urllib.request
import urllib.parse
url = 'https://www.baidu.com/s?wd='
headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0'
}
# 将周杰伦三个字变成unicode编码格式
# 我们需要依赖于urllib.parse
name = urllib.parse.quote('周杰伦')
url = url+name
re = urllib.request.Request(url, headers=headers)
response = urllib.request.urlopen(re)
# 获取响应内容
content = response.read().decode('utf-8')
urllib.request.urlretrieve(url, '周杰伦.html')
```

### get请求方式

```python
urllib.parse.urlencode() 适用于多个参数
# urlencoded多个参数的时候要使用
# data = {
#     'wd': '周杰伦',
#     'sex': '男'
# }
import urllib.parse
import  urllib.request
data = {
    'wd': '周杰伦',
    'sex': '男',
    'location': '中国台湾'
}
headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36 Edg/126.0.0.0'
}
base_url='https://www.baidu.com/s?'
data = urllib.parse.urlencode(data)
new_url=base_url+data
request = urllib.request.Request(url=new_url,headers=headers)
response = urllib.request.urlopen(request)
content = response.read().decode('utf-8')
print(content)
```

### post请求方式

```python
import urllib.parse
import urllib.request
import json
url = 'https://fanyi.baidu.com/sug'
headers = {
     'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36'
}
data = {
    'kw': 'spider'
}
# post请求的参数,必须进行编码
data = urllib.parse.urlencode(data).encode('utf-8')
request = urllib.request.Request(url, data=data, headers=headers)
urlopen = urllib.request.urlopen(request)
content = urlopen.read().decode('utf-8')
# 字符串->json
onj = json.loads(content)
print(onj.get('data').)
# post请求的参数必须编码 data = urllib.parse.urlencode(data).encode('utf-8')
# 编码之后必须调用encode方法 data=data.encode('utf-8')
# 参数是放在请求对象定制的方法中 request = urllib.request.Request(url, data=data, headers=headers)
```

# ajax

## ajax的get请求

```python
# get请求
# 获取豆瓣电影第一页的数据并且保存起来
import urllib.parse
import urllib.request
url = 'https://movie.douban.com/j/chart/top_list?type=5&interval_id=100%3A90&action=&start=0&limit=20'
headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36'
}
# (1)请求对象的定制
request = urllib.request.Request(url=url,headers=headers)
# 获取响应数据
response = urllib.request.urlopen(request)
content = response.read().decode('utf-8')
# open方法默认情况使用的gbk的编码，如果我们要想保存中文，需要在open中指定编码格式为utf-8
fp = open('douban.json','w',encoding='utf-8')
fp.write(content)
print('...编写完成')
```

  豆瓣电影

```python
import urllib.parse
import urllib.request


# 程序入口

def create_request(page):
    url = 'https://movie.douban.com/j/chart/top_list?type=5&interval_id=100%3A90&action=&'
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36'
    }
    data = {
        'start': (page - 1) * 20,
        'limit': 20
    }
    data = urllib.parse.urlencode(data)
    url = url + data
    request = urllib.request.Request(url=url, headers=headers)
    return request


def get_content(request):
    urlopen = urllib.request.urlopen(request)
    content = urlopen.read().decode('utf-8')
    return content


def download(content, page):
    with open('douban_第' + str(page) + '页.json', 'w', encoding='utf-8') as fp:
        fp.write(content)
        print('第' + str(page) + '页保存成功')


if __name__ == '__main__':
    start_page = int(input("请输入起始的页码"))
    end_page = int(input("请输入结束的页码"))
    for page in range(start_page, end_page + 1):
        request = create_request(page)
        content = get_content(request)
        download(content, page)
```

