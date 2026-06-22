# 卖家精灵数据开放平台 API 接口文档

> 认证方式：Header `secret-key: Your Secret`
> 通用返回结构：`{"code": "OK", "message": "成功", "data": {...}}`

---

## 查竞品

---

## 1. 查竞品

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/product/competitor-lookup`
- **MCP Code**：competitor_lookup
- **功能说明**：查目标ASIN的销量和销额等详细数据

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 市场编码，见附录-市场编码表 |
| month | String | 否 | 查询月份，格式：yyyyMM，示例：202507，见附录-市场周期表 |
| brand | String | 否 | 品牌 |
| sellerName | String | 否 | 卖家 |
| asins | List | 否 | asin的list字符串，最多支持40个ASIN |
| nodeIdPath | String | 否 | 类目节点字符串 |
| nodeIdPathEqual | boolean | 否 | 类目节点查询方式，true:为类目精确查询,false:为查询当前及子类目;默认：false |
| keyword | String | 否 | 关键字 |
| matchType | Integer | 否 | 关键词匹配方式，1：词组匹配，2：模糊匹配，3：精准匹配；默认：2 |
| variation | String | 否 | 是否查询变体ASIN，N:含变体,Y:不含变体 |
| page | Integer | 否 | 页码，默认：1 |
| size | Integer | 否 | 每页条数，默认：50，Max:100 |
| order | Object | 否 | 排序对象 |
| └field | String | 否 | 排序字段，默认：total_units，见附录-排序字段表 |
| └desc | boolean | 否 | 排序方式，true：desc，false：asc；默认：true |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| asin | String | asin |
| brand | String | 品牌 |
| brandUrl | String | 品牌URL |
| imageUrl | String | 图片URL |
| title | String | 商品标题 |
| parent | String | 父体 |
| nodeId | Long | 节点id |
| nodeIdPath | String | 节点id路径字符串 |
| nodeLabelPath | String | 类目 |
| symbol | String | 是否畅销，Y |
| bsrId | String | BSRid |
| bsr | Integer | BSR排名 |
| bsrCr | Float | BSR增长率 |
| bsrCv | Integer | BSR增长数 |
| units | Integer | 月销量(父体) |
| unitsGr | Float | 月销量增长率(父体) |
| amzUnit | Integer | 子体近30日销量 |
| amzSales | Float | 销售额(子体) |
| amzUnitDate | Date | 子体销量更新日期 |
| revenue | Float | 月销售额(父体) |
| price | Float | 价格 |
| primePrice | Float | prime价格，-1表示没有 |
| profit | Float | 利润率 |
| fba | Float | fba运费 |
| ratings | Integer | 评分数 |
| ratingsRate | Float | 留评率 |
| rating | Float | 评分 |
| ratingsCv | Integer | 月度增长数 |
| ratingDelta | Integer | 留评数：近30天新增评论数 |
| lqs | Float | listing质量得分 |
| availableDate | Long | 上架时间 |
| fulfillment | String | 配送方式，AMZ or FBA or FBM |
| variations | Integer | 变体数 |
| sellers | Integer | 卖家数 |
| sellerId | String | BuyBox卖家id |
| sellerName | String | BuyBox卖家 |
| sellerNation | String | BuyBox卖家国籍，见附录-卖家国籍表 |
| badge | Badge | 标识 |
| └bestSeller | String | Best Seller标识，Y / N |
| └amazonChoice | String | amazon choice标识，Y / N |
| └newRelease | String | release标识，Y / N |
| └ebc | String | A+页面，Y / N |
| └video | String | 视频介绍，Y / N |
| weight | String | 重量 |
| dimension | String | 尺寸 |
| dimensionsType | String | 尺寸类型 |
| pkgDimensions | String | 包装尺寸 |
| pkgDimensionType | String | 包装尺寸类型 |
| pkgWeight | String | 包装重量 |
| sku | String | sku |
| subcategories | List | 子类目 |
| └code | String | 类目code |
| └rank | Integer | 排名 |
| └label | String | 名称 |
| deliveryPrice | Float | 卖家运费,-1表示没有 |

---

## 选产品

---

## 2. 选产品

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/product/research`
- **MCP Code**：product_research
- **功能说明**：根据销量销额增长等各个维度设定条件来筛选产品

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 市场编码，见附录-市场编码表 |
| month | String | 否 | 查询月份，格式：yyyyMM，示例：202507，见附录-市场周期表 |
| keyword | String | 否 | 关键字 |
| includeSellers | String | 否 | 包含卖家 |
| excludeSellers | String | 否 | 排除卖家 |
| matchType | Integer | 否 | 匹配方式，1词组匹配 2模糊匹配 3精准匹配；默认2 |
| excludeKeywords | String | 否 | 排除的关键字 |
| minPrice | Float | 否 | 最低价格 |
| maxPrice | Float | 否 | 最高价格 |
| minRating | Float | 否 | 最低评分值 |
| maxRating | Float | 否 | 最高评分值 |
| minRatings | Integer | 否 | 最低评分数 |
| maxRatings | Integer | 否 | 最高评分数 |
| minRatingsCv | Integer | 否 | 最低月新增评分数 |
| maxRatingsCv | Integer | 否 | 最高月新增评分数 |
| minSellers | Integer | 否 | 最小卖家数量 |
| maxSellers | Integer | 否 | 最大卖家数量 |
| minProfit | Float | 否 | 最小毛利率 |
| maxProfit | Float | 否 | 最大毛利率 |
| minBsr | Integer | 否 | 最小BSR排名 |
| maxBsr | Integer | 否 | 最大BSR排名 |
| minUnits | Integer | 否 | 最小月销量 |
| maxUnits | Integer | 否 | 最大月销量 |
| minRevenue | Float | 否 | 最小销售额 |
| maxRevenue | Float | 否 | 最大销售额 |
| minUnitsGr | Float | 否 | 最小销量增长率 |
| maxUnitsGr | Float | 否 | 最大销量增长率 |
| minRevenueGr | Float | 否 | 最小销售额增长率 |
| maxRevenueGr | Float | 否 | 最大销售额增长率 |
| minAvailableDate | Long | 否 | 最早上架时间 |
| maxAvailableDate | Long | 否 | 最晚上架时间 |
| category | String | 否 | 类目节点字符串 |
| variations | Integer | 否 | 变体数量筛选 |
| minVariations | Integer | 否 | 最小变体数量 |
| maxVariations | Integer | 否 | 最大变体数量 |
| sellerType | String | 否 | 卖家类型，FBA/FBM/AMZ |
| fullInfo | Boolean | 否 | 是否返回全部信息 |
| page | Integer | 否 | 页码，从1开始 |
| size | Integer | 否 | 每页条数 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| asin | String | asin |
| brand | String | 品牌 |
| title | String | 商品标题 |
| imageUrl | String | 图片URL |
| parent | String | 父体 |
| marketplace | String | 市场 |
| nodeId | Long | 节点id |
| nodeIdPath | String | 节点id路径字符串 |
| nodeLabelPath | String | 类目 |
| bsrId | String | BSRid |
| bsr | Integer | BSR排名 |
| bsrCr | Float | BSR增长率 |
| bsrCv | Integer | BSR增长数 |
| units | Integer | 月销量(父体) |
| unitsGr | Float | 月销量增长率(父体) |
| revenue | Float | 月销售额(父体) |
| price | Float | 价格 |
| profit | Float | 利润率 |
| fba | Float | fba运费 |
| ratings | Integer | 评分数 |
| ratingsRate | Float | 留评率 |
| rating | Float | 评分 |
| ratingsCv | Integer | 月度增长数 |
| availableDate | Long | 上架时间 |
| fulfillment | String | 配送方式 |
| variations | Integer | 变体数 |
| sellers | Integer | 卖家数 |
| sellerName | String | BuyBox卖家 |
| sellerNation | String | BuyBox卖家国籍 |
| badge | Badge | 标识 |
| weight | String | 重量 |
| dimension | String | 尺寸 |

---

## 3. ASIN详情

- **HTTP方法**：GET
- **请求路径**：`https://api.sellersprite.com/v1/asin/{marketplace}/{asin}`
- **MCP Code**：asin_detail
- **功能说明**：ASIN的详情页面上各种信息集中展示，如上架日期，BSR排名，A+等等各种信息

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 市场，见附录-市场编码表 |
| asin | String | 是 | asin |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| asin | String | asin |
| asinUrl | String | asin url |
| availableDate | Long | 上架日期 |
| badge | Badge | 标识 |
| └bestSeller | String | Best Seller标识，Y / N |
| └amazonChoice | String | amazon choice标识，Y / N |
| └newRelease | String | release标识，Y / N |
| └ebc | String | A+页面，Y / N |
| └video | String | 视频介绍，Y / N |
| brand | String | 品牌 |
| brandUrl | String | 品牌URL |
| bsrId | String | bsr id |
| bsrLabel | String | bsr 标签 |
| bsrRank | Integer | bsr排名 |
| createdTime | Long | 创建时间 |
| dimensions | String | 尺寸 |
| firstRatingDate | Long | 第一次评论时间 |
| imageUrl | String | 图片链接 |
| lqs | Integer | Listing页面质量得分 |
| nodeId | String | 节点id |
| nodeIdPath | String | 节点id串 |
| nodeLabelPath | String | 类目名称串 |
| nodeLabelPathLocale | String | 类目名称串中文 |
| parent | String | 父asin |
| price | Float | 价格 |
| questions | Integer | 问题数量 |
| rating | Float | 评分 |
| ratings | Integer | 评分数 |
| reviews | Integer | 评论数 |
| variantRatings | Integer | 子体评分数 |
| variantReviews | Integer | 子体评论数 |
| sellerId | String | 卖家id |
| sellerName | String | 卖家名称 |
| fulfillment | String | 配送方式 |
| sellers | Integer | 卖家数 |
| skuList | List | sku |
| marketplace | String | 市场 |
| title | String | 标题 |
| features | List | 五点描述 |
| overviews | String | 详情，json格式字符串 |
| updatedTime | Long | 更新时间 |
| variationList | List | 变体 |
| variations | Integer | 变体数量 |
| weight | String | 重量 |
| zoomImageUrl | String | 大图URL |
| subcategories | Object | 子类目信息 |
| └rank | Integer | 子类目排名 |
| └code | String | 子类目code |
| └label | String | 子类目标签 |
| deliveryPrice | Float | 卖家运费,-1表示没有 |
| primePrice | Float | prime价格，-1表示没有 |
| coupon | String | 优惠卷 |

---

## 4. 查产品类目

- **HTTP方法**：GET
- **请求路径**：`https://api.sellersprite.com/v1/product/node`
- **MCP Code**：product_node
- **功能说明**：类目ID，名称，所有节点名称及类目下产品数量

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 市场，见附录-市场编码表 |
| nodeIdPath | String | 否 | 类目节点id字符串 |
| keyword | String | 否 | 搜索关键字，nodeId或类目名称 |
| month | String | 否 | 查询历史月份类目，格式yyyyMM |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| nodeIdPath | String | 类目id字符串，即nodeIdPath |
| nodeLabelPath | String | 类目名称 |
| products | Integer | 类目下产品数 |
| nodeLabelLocale | String | 类目节点名称中文 |
| nodeLabelPathLocale | String | 类目所属所有节点名称中文 |

---

## 关键词相关

---

## 5. 关键词挖掘

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/keyword/miner`
- **MCP Code**：keyword_miner
- **功能说明**：拓展关键词的衍生词以及含有该词根的长尾关键词

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 市场，见附录-市场编码表 |
| historyDate | String | 否 | 历史日期，yyyyMM格式，最近30天不传或传空字符串 |
| keyword | String | 是 | 关键词 |
| keywordList | List | 否 | 批量查询关键词 |
| minSearch | Integer | 否 | 最小搜索量 |
| maxSearch | Integer | 否 | 最大搜索量 |
| minPurchases | Integer | 否 | 最小购买量 |
| maxPurchases | Integer | 否 | 最大购买量 |
| minPurchasesRate | Float | 否 | 最小购买率 |
| maxPurchasesRate | Float | 否 | 最大购买率 |
| minSPR | Integer | 否 | 最小SPR |
| maxSPR | Integer | 否 | 最大SPR |
| minTitleDensity | Integer | 否 | 最小标题密度 |
| maxTitleDensity | Integer | 否 | 最大标题密度 |
| minRelevancy | Float | 否 | 最小相关度，最小0 |
| maxRelevancy | Float | 否 | 最大相关度，最大100 |
| minSearchRank | Integer | 否 | 最小搜索排名 |
| maxSearchRank | Integer | 否 | 最大搜索排名 |
| minProducts | Integer | 否 | 最小商品数 |
| maxProducts | Integer | 否 | 最大商品数 |
| minSupplyDemandRatio | Float | 否 | 最小供需比 |
| maxSupplyDemandRatio | Float | 否 | 最大供需比 |
| minAdProducts | Integer | 否 | 最小广告竞品数 |
| maxAdProducts | Integer | 否 | 最大广告竞品数 |
| minWordCount | Integer | 否 | 最小单词个数 |
| maxWordCount | Integer | 否 | 最大单词个数 |
| minMonopolyClickRate | Float | 否 | 最小点击集中度 |
| maxMonopolyClickRate | Float | 否 | 最大点击集中度 |
| minBid | Float | 否 | 最小ppc竞价 |
| maxBid | Float | 否 | 最大ppc竞价 |
| minPrice | Float | 否 | 最小均价 |
| maxPrice | Float | 否 | 最大均价 |
| minRatings | Integer | 否 | 最小评分数 |
| maxRatings | Integer | 否 | 最大评分数 |
| minRating | Float | 否 | 最小评分值 |
| maxRating | Float | 否 | 最大评分值 |
| amazonChoice | Boolean | 否 | 亚马逊推荐词 |
| filterRootWord | Integer | 否 | 过滤词根，0包含所有 1只包含词根 |
| matchType | Integer | 否 | 2:广泛匹配, 3:词组匹配 |
| includeKeywords | List | 否 | 包含的词 |
| excludeKeywords | List | 否 | 排除的词 |
| page | Integer | 否 | 页码，从1开始，默认：1 |
| size | Integer | 否 | 每页条数，默认：50，最大：100 |
| order | Object | 否 | 排序 |
| └field | String | 否 | 排序字段 |
| └desc | boolean | 否 | true为降序 false为升序，默认降序 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| marketplace | String | 市场 |
| keyword | String | 关键词 |
| keywordCn | String | 关键词中文翻译 |
| keywordJp | String | 关键词日文翻译 |
| departments | List | 类目 |
| └code | String | 类目代码 |
| └label | String | 类目名称 |
| month | String | 搜索月份 |
| supplement | String | 是否属于补充关键词（无当前月搜索量） |
| searches | Integer | 搜索量 |
| purchases | Integer | 月购买量 |
| purchaseRate | Float | 月购买率 |
| monopolyClickRate | Float | 点击垄断率 |
| products | Integer | 商品数 |
| adProducts | Integer | 广告竞品数 |
| supplyDemandRatio | Float | 供需比 |
| avgPrice | Float | 平均价格 |
| avgRatings | Integer | 平均评分数 |
| avgRating | Float | 平均评分值 |
| bidMin | Float | 最小PPC价格 |
| bidMax | Float | 最大PPC价格 |
| bid | Float | PPC价格 |
| cvsShareRate | Float | 转化共享率 |
| wordCount | Integer | 单词个数 |
| titleDensity | Integer | 标题密度 |
| spr | Integer | SPR |
| relevancy | Double | 相关度 |
| amazonChoice | Boolean | 亚马逊推荐词 |
| searchRank | Integer | 搜索排名 |

---

## 6. 关键词选品

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/keyword-research`
- **MCP Code**：keyword_research
- **功能说明**：通过关键词的月搜索量、购买率等数据，找出其对应细分市场中蕴藏的商机

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 市场，见附录-市场编码表 |
| month | String | 否 | 筛选日期,yyyyMM格式，支持近24个月的 |
| departments | List | 否 | 查询类目，传递code |
| keywords | String | 否 | 关键词 |
| excludeKeywords | String | 否 | 排除的关键字 |
| minSearches | Integer | 否 | 最小月搜索量 |
| maxSearches | Integer | 否 | 最大月搜索量 |
| minSearchesCr | Float | 否 | 最小月搜索量增长率 |
| maxSearchesCr | Float | 否 | 最大月搜索量增长率 |
| minProducts | Integer | 否 | 最小商品数 |
| maxProducts | Integer | 否 | 最大商品数 |
| minPurchases | Integer | 否 | 最小购买量 |
| maxPurchases | Integer | 否 | 最大购买量 |
| minPurchaseRate | Float | 否 | 最小购买率 |
| maxPurchaseRate | Float | 否 | 最大购买率 |
| withYearlyGrowth | Boolean | 否 | 新细分市场 |
| minSearchMonthCv | Integer | 否 | 最小月搜索量同比增长值 |
| maxSearchMonthCv | Integer | 否 | 最大月搜索量同比增长值 |
| minRankGrowthRate | Float | 否 | 最小排名增长率 |
| maxRankGrowthRate | Float | 否 | 最大排名增长率 |
| minMonopolyClickRate | Float | 否 | 最小点击集中度 |
| maxMonopolyClickRate | Float | 否 | 最大点击集中度 |
| minTitleDensity | Integer | 否 | 最小标题密度 |
| maxTitleDensity | Integer | 否 | 最大标题密度 |
| minSPR | Integer | 否 | 最小SPR |
| maxSPR | Integer | 否 | 最大SPR |
| minBid | Float | 否 | 最小PPC竞价 |
| maxBid | Float | 否 | 最大PPC竞价 |
| minAvgPrice | Float | 否 | 最小平均价格 |
| maxAvgPrice | Float | 否 | 最大平均价格 |
| page | Integer | 否 | 页码，从1开始，默认：1 |
| size | Integer | 否 | 每页条数，最大50 |
| order | Object | 否 | 排序 |
| └field | String | 否 | 排序字段，见附录-排序字段表 |
| └desc | boolean | 否 | true为降序false为升序，默认降序 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| marketplace | String | 市场 |
| department | String | 类目 |
| departmentCode | String | 类目code |
| keyword | String | 关键词 |
| keywordCn | String | 关键词中文 |
| searches | Integer | 月搜索量 |
| searchesCr | Float | 搜索量增长率 |
| products | Integer | 商品数 |
| purchases | Integer | 月购买量 |
| purchaseRate | Float | 购买率 |
| yearlyGrowth | Boolean | 是否有年均增长 |
| avgPrice | Float | 平均价格 |
| avgRating | Float | 平均评分 |
| avgReviews | Integer | 平均评分数 |
| spr | Integer | SPR |
| bid | Float | PPC竞价 |
| rank | Integer | 排名 |
| titleDensity | Integer | 标题密度 |
| monopolyClickRate | Float | 点击集中度 |

---

## 7. 关键词选品-趋势数据

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/keyword-research/trends`
- **MCP Code**：keyword_research_trends
- **功能说明**：通过关键词查询此词的历史趋势数据

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 市场，见附录-市场编码表 |
| keyword | String | 是 | 关键词 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| time | String | 时间 |
| keyword | String | 关键词 |
| keywordCn | String | 关键词-中文 |
| keywordJp | String | 关键词-日文 |
| search | Integer | 搜索量 |
| purchase | BigDecimal | 购买量 |
| purchaseRate | BigDecimal | 购买率 |
| yearlyGrowth | BigDecimal | 同比增长率 |
| chainGrowth | BigDecimal | 环比增长率 |
| threeMonthGrowth | BigDecimal | 三个月增长率 |

---

## 8. 谷歌趋势

- **HTTP方法**：GET
- **请求路径**：`https://api.sellersprite.com/v1/google/trends`
- **MCP Code**：google_trend
- **功能说明**：关键词的谷歌趋势

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 市场，见附录-市场编码表 |
| keyword | String | 否 | 关键字 |
| googleProp | String | 否 | 类别，web:google网页搜索 shoppingCart:google购物搜索 |
| monthly | boolean | 否 | 按照月份，默认false |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| marketplace | String | 市场 |
| keyword | String | 关键字 |
| link | String | google trend链接 |
| items | List | 明细 |
| └time | Long | 时间戳 |
| └value | Integer | 值 |

---

## 9. ABA数据选品-按周

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/aba/research/weekly`
- **MCP Code**：aba_research_weekly
- **功能说明**：根据关键词的周各种参数来筛选产品

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 市场，见附录-市场编码表 |
| date | String | 否 | 为空时，查最新周，格式：yyyyMMdd（限定为周六的日期） |
| departments | List | 否 | 类目列表 |
| excludeKeywords | String | 否 | 排除关键词 |
| includeKeywords | String | 否 | 包含关键词 |
| exactFlag | Boolean | 否 | 是否精确匹配 |
| rankGrowthValue | Integer | 否 | 搜索增长量 |
| rankGrowthRate | Double | 否 | 搜索增长率 |
| minRankGrowthRate | Double | 否 | 最小排名增长率 |
| maxRankGrowthRate | Double | 否 | 最大排名增长率 |
| minSearchRank | Integer | 否 | 最小排名 |
| maxSearchRank | Integer | 否 | 最大排名 |
| minSearches | Integer | 否 | 最小搜索量 |
| maxSearches | Integer | 否 | 最大搜索量 |
| minMonopolyClickRate | Double | 否 | 最小点击集中度 |
| maxMonopolyClickRate | Double | 否 | 最大点击集中度 |
| minConversionRate | Double | 否 | 最小转化占比 |
| maxConversionRate | Double | 否 | 最大转化占比 |
| minWordCount | Integer | 否 | 最小单词数 |
| maxWordCount | Integer | 否 | 最大单词数 |
| minSPR | Integer | 否 | 最小SPR |
| maxSPR | Integer | 否 | 最大SPR |
| minTitleDensity | Integer | 否 | 最小标题密度 |
| maxTitleDensity | Integer | 否 | 最大标题密度 |
| minClicks | Integer | 否 | 最小点击量 |
| maxClicks | Integer | 否 | 最大点击量 |
| minImpressions | Integer | 否 | 最小展示量 |
| maxImpressions | Integer | 否 | 最大展示量 |
| searchModel | Integer | 否 | 搜索模式：1：热门市场 2：异动市场 3：持续增长市场 4：快速飙升市场 5：潜力市场 6：长尾市场 |
| page | Integer | 否 | 页码，从1开始，默认：1 |
| size | Integer | 否 | 每页条数，最大40 |
| order | Object | 否 | 排序 |
| └field | String | 否 | 排序字段 |
| └desc | boolean | 否 | true为降序 false为升序，默认降序 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| marketplace | String | 市场 |
| date | String | 查询日期，限定为周六的日期 |
| keyword | String | 关键词 |
| keywordCn | String | 关键词中文 |
| keywordJp | String | 关键词日文 |
| departments | List | 类目 |
| searchRank | Integer | 搜索排名 |
| searchRankCv | Integer | 排名增长量 |
| searchRankCr | Double | 排名增长率 |
| searches | Integer | 搜索量 |
| purchases | Integer | 购买量 |
| purchaseRate | Double | 购买率 |
| clicks | Integer | 点击量 |
| impressions | BigInteger | 展示量 |
| titleDensityExact | Integer | 首页商品标题中包含该关键词的商品数(精确匹配) |
| cprExact | Integer | 精确CPR（8天内确保关键词上首页的销量数） |
| w1SearchRank | Integer | 上周的排名 |
| w1RankGrowthValue | Integer | 上周的排名变化值 |
| w1RankGrowthRate | Double | 上周的排名变化率 |
| w4SearchRank | Integer | 4周前的排名 |
| w4RankGrowthValue | Integer | 4周前的排名变化值 |
| w4RankGrowthRate | Double | 4周前的排名变化率 |
| w12SearchRank | Integer | 12周前的排名 |
| w12RankGrowthValue | Integer | 12周前的排名变化值 |
| w12RankGrowthRate | Double | 12周前的排名变化率 |
| top3Brands | List | 点击前三品牌 |
| bid | Float | ppc竞价 |
| bidMax | Float | 最大ppc竞价 |
| bidMin | Float | 最小ppc竞价 |
| top3AsinDtoList | List | 前三点击asin |
| └asin | String | asin |
| └imageUrl | String | 图片URL |
| └clickRate | Double | 点击集中度 |
| └conversionRate | Double | 转化率 |
| clickShareRate | Double | 前三点击比 |
| cvsShareRate | Double | 前三转化总比 |

---

## 10. ABA数据选品-按月

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/aba/research/monthly`
- **MCP Code**：aba_research_monthly
- **功能说明**：根据关键词的月度各种参数来筛选产品

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 市场，见附录-市场编码表 |
| date | String | 否 | 为空时，查最近30天，格式：yyyyMM |
| departments | List | 否 | 类目列表 |
| excludeKeywords | String | 否 | 排除关键词 |
| includeKeywords | String | 否 | 包含关键词 |
| exactFlag | Boolean | 否 | 是否精确匹配 |
| minRankGrowthRate | Double | 否 | 最小排名增长率 |
| maxRankGrowthRate | Double | 否 | 最大排名增长率 |
| minSearchRank | Integer | 否 | 最小排名 |
| maxSearchRank | Integer | 否 | 最大排名 |
| minSearches | Integer | 否 | 最小搜索量 |
| maxSearches | Integer | 否 | 最大搜索量 |
| minMonopolyClickRate | Double | 否 | 最小点击集中度 |
| maxMonopolyClickRate | Double | 否 | 最大点击集中度 |
| minConversionRate | Double | 否 | 最小转化占比 |
| maxConversionRate | Double | 否 | 最大转化占比 |
| minWordCount | Integer | 否 | 最小单词数 |
| maxWordCount | Integer | 否 | 最大单词数 |
| minSPR | Integer | 否 | 最小SPR |
| maxSPR | Integer | 否 | 最大SPR |
| minTitleDensity | Integer | 否 | 最小标题密度 |
| maxTitleDensity | Integer | 否 | 最大标题密度 |
| minClicks | Integer | 否 | 最小点击量 |
| maxClicks | Integer | 否 | 最大点击量 |
| minImpressions | Integer | 否 | 最小展示量 |
| maxImpressions | Integer | 否 | 最大展示量 |
| searchModel | Integer | 否 | 搜索模式：1：热门市场 2：异动市场 3：持续增长市场 4：快速飙升市场 5：潜力市场 6：长尾市场 |
| page | Integer | 否 | 页码，从1开始，默认：1 |
| size | Integer | 否 | 每页条数，最大15 |
| order | Object | 否 | 排序 |
| └field | String | 否 | 排序字段 |
| └desc | boolean | 否 | true为降序 false为升序，默认降序 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| marketplace | String | 市场 |
| date | String | 查询日期 |
| keyword | String | 关键词 |
| keywordCn | String | 关键词中文 |
| keywordJp | String | 关键词日文 |
| departments | List | 类目 |
| searchRank | Integer | 搜索排名 |
| searchRankCv | Integer | 排名增长量 |
| searchRankCr | Double | 排名增长率 |
| searches | Integer | 搜索量 |
| purchases | Integer | 购买量 |
| purchaseRate | Double | 购买率 |
| clicks | Integer | 点击量 |
| impressions | BigInteger | 展示量 |
| titleDensityExact | Integer | 首页商品标题中包含该关键词的商品数(精确匹配) |
| cprExact | Integer | 精确CPR |
| w1SearchRank | Integer | 上周的排名 |
| w1RankGrowthValue | Integer | 上周的排名变化值 |
| w1RankGrowthRate | Double | 上周的排名变化率 |
| w4SearchRank | Integer | 4周前的排名 |
| w4RankGrowthValue | Integer | 4周前的排名变化值 |
| w4RankGrowthRate | Double | 4周前的排名变化率 |
| w12SearchRank | Integer | 12周前的排名 |
| w12RankGrowthValue | Integer | 12周前的排名变化值 |
| w12RankGrowthRate | Double | 12周前的排名变化率 |
| top3Brands | List | 点击前三品牌 |
| bid | Float | ppc竞价 |
| bidMax | Float | 最大ppc竞价 |
| bidMin | Float | 最小ppc竞价 |
| top3AsinDtoList | List | 前三点击asin |
| └asin | String | asin |
| └imageUrl | String | 图片URL |
| └clickRate | Double | 点击集中度 |
| └conversionRate | Double | 转化率 |
| clickShareRate | Double | 前三点击比 |
| cvsShareRate | Double | 前三转化总比 |

---

## 11. ABA数据选品-关键词趋势

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/aba/research/trends`
- **MCP Code**：aba_research_trend
- **功能说明**：查询关键词的历史趋势数据

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 市场，见附录-市场编码表 |
| keyword | String | 是 | 关键词 |
| timeGranularity | String | 否 | 时间粒度，W：周，M：月 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| date | Date | 日期 |
| rank | String | ABA排名 |
| searches | String | 搜索量 |
| label | Integer | 日期标签 |

---

## 12. 出单词反查

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/keyword-order`
- **MCP Code**：keyword_order
- **功能说明**：出单词反查工具用于反查任一目标ASIN的Top出单词，反查竞品的出单词，用于优化自己的Listing或用于精准广告词的投放

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 市场，见附录-市场编码表 |
| asins | List | 是 | asin列表，最大20 |
| reverseType | String | 是 | 反查模式，W-周 M-月 |
| date | String | 否 | 查询日期，按周查格式为yyyMMdd，按月查询yyyyMM |
| conversionType | List | 否 | 转化类型：E：转化优质词，S：转化平稳词，L：转化流失词，I：无效曝光词 |
| variation | List | 否 | 是否查询变体asin：Y:否 N:是 |
| page | Integer | 否 | 当前页，默认1 |
| size | Integer | 否 | 每页显示多少条，固定50 |
| order | Object | 否 | 排序 |
| └field | String | 否 | 排序字段 |
| └desc | Boolean | 否 | 是否倒序，默认false |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| marketplace | String | 市场 |
| keyword | String | 关键词 |
| keywordCn | String | 关键词中文翻译 |
| keywordJp | String | 关键词日文翻译 |
| asin | String | 所属asin |
| searches | Integer | 搜索量 |
| monopolyClickRate | Float | 点击垄断率 |
| cvsShareRate | Float | 转化共享率 |
| searchRank | Integer | 搜索排名 |
| searchRankGv | Integer | 月变化量 |
| searchRankGr | Double | 月变化率 |
| top3ClickingRate | Float | 前三点击 |
| top3ConversionRate | Float | 前三转化 |
| conversionType | String | 化类型：E：转化优质词，S：转化平稳词，L：转化流失词，I：无效曝光词 |

---

## 13. 拓展流量词

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/traffic/extend`
- **MCP Code**：traffic_extend
- **功能说明**：ASIN在近30天内进入过亚马逊前台搜索结果前3页的所有搜索流量词，支持多个ASIN

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 市场，见附录-市场编码表 |
| historyDate | String | 否 | 历史日期，yyyyMM格式，最近30天不传或传空字符串 |
| asinList | List | 是 | asin列表，最多20 |
| queryType | Integer | 否 | 查询方式，0所有变体 1畅销变体 2当前变体，默认2 |
| minSearches | Integer | 否 | 最小月搜索量 |
| maxSearches | Integer | 否 | 最大月搜索量 |
| minSearchRank | Integer | 否 | 最小搜索排名 |
| maxSearchRank | Integer | 否 | 最大搜索排名 |
| minPurchases | Integer | 否 | 最小购买量 |
| maxPurchases | Integer | 否 | 最大购买量 |
| minPurchaseRate | Float | 否 | 最小购买率 |
| maxPurchaseRate | Float | 否 | 最大购买率 |
| minProducts | Integer | 否 | 最小商品数 |
| maxProducts | Integer | 否 | 最大商品数 |
| minSupplyDemandRatio | Float | 否 | 最小供需比 |
| maxSupplyDemandRatio | Float | 否 | 最大供需比 |
| minBid | Float | 否 | 最小ppc竞价 |
| maxBid | Float | 否 | 最大ppc竞价 |
| minAdProducts | Integer | 否 | 最小广告竞品数 |
| maxAdProducts | Integer | 否 | 最大广告竞品数 |
| minTitleDensity | Integer | 否 | 最小标题密度 |
| maxTitleDensity | Integer | 否 | 最大标题密度 |
| minSPR | Integer | 否 | 最小SPR |
| maxSPR | Integer | 否 | 最大SPR |
| minRelevancy | Float | 否 | 最小相关度 |
| maxRelevancy | Float | 否 | 最大相关度 |
| minMonopolyClickRate | Float | 否 | 最小点击集中度 |
| maxMonopolyClickRate | Float | 否 | 最大点击集中度 |
| includeKeywords | List | 否 | 包含的词 |
| excludeKeywords | List | 否 | 排除的词 |
| minWordCount | Integer | 否 | 最小单词个数 |
| maxWordCount | Integer | 否 | 最大单词个数 |
| badges | List | 否 | 流量词类型 |
| page | Integer | 否 | 页码，从1开始，默认：1 |
| size | Integer | 否 | 每页条数，默认：50，最大：100 |
| order | Object | 否 | 排序 |
| └field | String | 否 | 排序字段 |
| └desc | boolean | 否 | true为降序false为升序，默认降序 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| marketplace | String | 市场 |
| keyword | String | 关键词 |
| keywordCn | String | 关键词中文翻译 |
| searches | Integer | 搜索量 |
| purchases | Integer | 月购买量 |
| purchaseRate | Float | 购买率 |
| products | Integer | 商品数 |
| adProducts | Integer | 广告竞品数 |
| supplyDemandRatio | Float | 供需比 |
| bid | Float | PPC竞价 |
| titleDensity | Integer | 标题密度 |
| spr | Integer | SPR |
| relevancy | Double | 相关度 |
| monopolyClickRate | Float | 点击集中度 |
| searchRank | Integer | 搜索排名 |
| badges | List | 流量词类型 |
| asin | String | asin |
| clickRate | Float | 点击率 |
| conversionRate | Float | 转化率 |

---

## 流量词相关

---

## 14. 流量词统计

- **HTTP方法**：GET
- **请求路径**：`https://api.sellersprite.com/v1/traffic/keyword/stat/{marketplace}/{asin}`
- **MCP Code**：traffic_keyword_stat
- **功能说明**：查询ASIN的流量词统计数据

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 市场，见附录-市场编码表 |
| asin | String | 是 | asin |
| month | String | 否 | 查询月份 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| marketplace | String | 市场 |
| asin | String | asin |
| keywords | Integer | 全部流量词条数 |
| ranks | Integer | 自然流量词条数 |
| ads | Integer | 广告流量词条数 |
| calcTime | Long | 最近计算时间 |
| badgeCount | Object | 流量词类型统计 |
| └ns | Integer | 自然搜索词数量 |
| └ac | Integer | AC推荐词数量 |
| └er | Integer | ER推荐词数量 |
| └fs | Integer | 4星推荐词数量 |
| └hr | Integer | HR广告词数量 |
| └sb | Integer | 品牌广告词数量 |
| └sv | Integer | 视频广告词数量 |
| └ad | Integer | SP广告词数量 |

---

## 15. 关键词反查(流量词列表)

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/traffic/keyword`
- **MCP Code**：traffic_keyword
- **功能说明**：查询ASIN在近30天内进入过亚马逊前台搜索结果前3页的所有搜索流量词

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 市场，见附录-市场编码表 |
| asin | String | 是 | asin |
| keyword | String | 否 | 关键词 |
| month | String | 否 | 历史月份，不传默认最近30天 |
| badges | List | 否 | 流量词类型 |
| trafficKeywordTypes | List | 否 | 流量占比类型 |
| conversionKeywordTypes | List | 否 | 流量转化类型 |
| page | Integer | 否 | 当前页，默认1 |
| size | Integer | 否 | 每页显示多少条，默认50，最大100，最多查询2000条数据 |
| order | Object | 否 | 排序 |
| └field | String | 否 | 排序字段，默认：rankPosition |
| └desc | Boolean | 否 | 是否倒序，默认false |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| marketplace | String | 市场编码 |
| asin | String | asin |
| total | Integer | 总条数 |
| items | List | 词条 |
| └keyword | String | 关键词 |
| └keywordCn | String | 关键词中文翻译 |
| └searches | Integer | 月搜索量 |
| └products | Integer | 商品数 |
| └purchases | Integer | 月购买量 |
| └purchaseRate | Float | 购买率 |
| └bid | Float | PPC竞价 |
| └bidMax | Float | PPC竞价范围 |
| └bidMin | Float | PPC竞价范围 |
| └badges | List | 曝光位置 |
| └rankPosition | RankPosition | 自然排名 |
| └└page | Integer | 第几页 |
| └└pageSize | Integer | 每页多少条数据 |
| └└index | Integer | 当前页排第几 |
| └└position | Integer | 总结果中排第几 |
| └└updatedTime | long | 排名时间 |
| └adPosition | AdPosition | 广告排名 |
| └searchesRank | Integer | 周搜索量排名 |
| └searchesRankTimeFrom | Long | 周搜索量排名时间范围 |
| └searchesRankTimeTo | Long | searchesRankTimeTo |
| └latest1daysAds | Integer | 最近1天广告竞品数 |
| └latest7daysAds | Integer | 最近7天广告竞品数 |
| └latest30daysAds | Integer | 最近30天广告竞品数 |
| └supplyDemandRatio | Float | 供需比 |
| └trafficPercentage | Float | 流量占比 |
| └trafficKeywordType | String | 流量占比类型 |
| └conversionKeywordType | String | 转换效果类型 |
| └calculatedWeeklySearches | Float | 预估周曝光量 |
| └impressions | Long | 展示量 |
| └updatedTime | Long | 更新时间 |
| └clicks | Integer | 点击量 |
| └naturalRatio | Float | 流量分布-自然占比 |
| └adRatio | Float | 流量分布-广告占比 |
| └stats | List | 高频词 |
| └keywords | String | 词 |
| └total | Integer | 总条数 |

---

## 16. 关联流量统计

- **HTTP方法**：GET
- **请求路径**：`https://api.sellersprite.com/v1/traffic/listing/stat/{marketplace}/{asin}`
- **MCP Code**：traffic_listing_stat
- **功能说明**：查询ASIN的关联流量统计数据

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 市场，见附录-市场编码表 |
| asinList | List | 否 | asin列表 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| marketplace | String | 市场 |
| asin | String | asin |
| relations | Integer | 全部流量 |
| freeRelations | Integer | 免费流量 |
| paidRelations | Integer | 付费流量 |
| calcTime | Long | 最近计算时间 |
| items | List | 统计概要 |
| └relation | String | 关联类型 |
| └count | Integer | 数量 |

---

## 17. 关联流量列表

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/traffic/listing/page`
- **MCP Code**：traffic_listing
- **功能说明**：查询产品及其变体的关联流量来源

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 市场，见附录-市场编码表 |
| asinList | List | 是 | asin列表 |
| relations | List | 是 | 关联类型 |
| variations | Boolean | 否 | 是否查询变体，默认false |
| page | Integer | 否 | 页码，从1开始，默认：1 |
| size | Integer | 否 | 每页条数，默认：50 |
| order | Object | 否 | 排序 |
| └field | String | 否 | 排序字段 |
| └desc | boolean | 否 | true为降序false为升序，默认降序 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| asin | String | asin |
| brand | String | 品牌 |
| brandUrl | String | 品牌URL |
| imageUrl | String | 图片URL |
| title | String | 商品标题 |
| parent | String | 父体 |
| nodeId | Long | 节点id |
| nodeIdPath | String | 节点id路径字符串 |
| nodeLabelPath | String | 类目 |
| bsrId | String | BSRid |
| bsr | Integer | BSR排名 |
| units | Integer | 月销量 |
| unitsCr | Float | 月销量增长率 |
| revenue | Float | 月销售额 |
| price | Float | 价格 |
| profit | Float | 利润率 |
| fba | Float | fba运费 |
| ratings | Integer | 评分数 |
| ratingsRate | Float | 留评率 |
| rating | Float | 评分 |
| ratingsCv | Integer | 月度增长数 |
| ratingDelta | Integer | 留评数：近30天新增评论数 |
| availableDate | Long | 上架时间，时间戳格式 |
| fulfillment | String | 配送方式，AMZ or FBA or FBM |
| variations | Integer | 变体数 |
| sellers | Integer | 卖家数 |
| sellerId | String | BuyBox卖家id |
| sellerName | String | BuyBox卖家 |
| sellerNation | String | BuyBox卖家国籍，见附录-卖家国籍表 |
| badge | Badge | 标识 |
| └bestSeller | String | Best Seller标识，Y / N |
| └amazonChoice | String | amazon choice标识，Y / N |
| └newRelease | String | release标识，Y / N |
| └ebc | String | A+页面，Y / N |
| └video | String | 视频介绍，Y / N |
| weight | String | 重量 |
| dimension | String | 尺寸 |
| dimensionType | String | 尺寸类型 |
| sku | String | sku |

---

## 18. 查流量来源(关键词流向)

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/traffic/source`
- **MCP Code**：traffic_source
- **功能说明**：查询ASIN的流量来源构成

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 市场，见附录-市场编码表 |
| q | String | 是 | asin或者关键词 |
| month | String | 是 | 筛选日期,yyyyMM格式 |
| page | Integer | 否 | 页码，从1开始，默认：1 |
| size | Integer | 否 | 每页条数，默认：50最大：100 |
| order | Object | 否 | 排序 |
| └field | String | 否 | 排序字段 |
| └desc | boolean | 否 | true为降序false为升序，默认降序 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| keywords | Integer | 全部流量词 |
| searchKeywords | Integer | 自然搜索词 |
| acKeywords | String | AC推荐词 |
| editorialKeywords | Integer | ER推荐词 |
| fourStarsKeywords | Integer | 4星推荐词 |
| hrKeywords | Integer | HR推荐词 |
| adKeywords | Integer | SP广告词 |
| videoKeywords | Integer | 视频广告词 |
| brandKeywords | Integer | 品牌广告词 |
| badgeLabels | List | 流量来源概览 |
| badgeDetails | Map | 流量来源明细 |
| asinInfo | Object | Asin相关信息 |
| └asin | String | asin |
| └asinUrl | String | 该asin对应亚马逊地址 |
| └currency | String | 货币code |
| └price | Float | 价格 |
| └rating | Float | 评分 |
| └reviews | Integer | 评分数 |
| └title | String | 标题 |
| └sku | String | sku |
| └variations | Integer | 变体数 |
| └nodeId | Long | 类目ID |
| └nodeIdPath | String | 类目ID路径 |
| └nodeLabelPath | String | 类目路径 |
| └bsrRank | Long | 大类排名(BSR) |

---

## 市场分析

---

## 19. 选市场列表

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/market/research`
- **MCP Code**：market_research
- **功能说明**：查询细分类目的市场分析数据

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 站点编码，见附录-市场编码表 |
| month | String | 否 | 筛选日期,默认最近30天，见附录-市场周期表 |
| topNum | Integer | 否 | 头部Listing数量 |
| newProduct | Integer | 否 | 新品定义，默认：3 |
| nodeIdPath | String | 否 | 类目 |
| departmentKeyword | String | 否 | 类目关键字 |
| minAvgUnits | Integer | 否 | 最低月均销量 |
| maxAvgUnits | Integer | 否 | 最高月均销量 |
| minAvgRevenue | Float | 否 | 最低月均销售额 |
| maxAvgRevenue | Float | 否 | 最高月均销售额 |
| minAvgRatings | Integer | 否 | 最低平均评分数 |
| maxAvgRatings | Integer | 否 | 最高平均评分数 |
| minAvgRating | Float | 否 | 最低平均评分值 |
| maxAvgRating | Float | 否 | 最高平均评分值 |
| minAvgBsr | Integer | 否 | 最低平均BSR排名 |
| maxAvgBsr | Integer | 否 | 最高平均BSR排名 |
| minAvgPrice | Float | 否 | 最低平均价格 |
| maxAvgPrice | Float | 否 | 最高平均价格 |
| minWeight | Float | 否 | 最低重量 |
| maxWeight | Float | 否 | 最高重量 |
| minProfit | Float | 否 | 最低毛利率 |
| maxProfit | Float | 否 | 最高毛利率 |
| minSellers | Integer | 否 | 最低卖家数 |
| maxSellers | Integer | 否 | 最高卖家数 |
| minUnits | Integer | 否 | 最低销量 |
| maxUnits | Integer | 否 | 最高销量 |
| minRevenue | Float | 否 | 最低销售额 |
| maxRevenue | Float | 否 | 最高销售额 |
| minRatings | Integer | 否 | 最低评分数 |
| maxRatings | Integer | 否 | 最高评分数 |
| minRating | Float | 否 | 最低评分值 |
| maxRating | Float | 否 | 最高评分值 |
| minBsr | Integer | 否 | 最低BSR排名 |
| maxBsr | Integer | 否 | 最高BSR排名 |
| minPrice | Float | 否 | 最低价格 |
| maxPrice | Float | 否 | 最高价格 |
| minNewProduct | Integer | 否 | 新品数量下限 |
| maxNewProduct | Integer | 否 | 新品数量上限 |
| minNewProductRatio | Float | 否 | 新品数量占比下限 |
| maxNewProductRatio | Float | 否 | 新品数量占比上限 |
| minHlUnits | Integer | 否 | 头部Listing前N名商品最低月均销量 |
| maxHlUnits | Integer | 否 | 头部Listing前N名商品最高月均销量 |
| minHlRevenue | Float | 否 | 头部Listing前N名商品最低月均销售额 |
| maxHlRevenue | Float | 否 | 头部Listing前N名商品最高月均销售额 |
| minHlPrice | Float | 否 | 头部Listing前N名商品最低平均价格 |
| maxHlPrice | Float | 否 | 头部Listing前N名商品最高平均价格 |
| minHlRatings | Integer | 否 | 头部Listing前N名商品最低评论数 |
| maxHlRatings | Integer | 否 | 头部Listing前N名商品最高评论数 |
| minHlRating | Float | 否 | 头部Listing前N名商品最低评分值 |
| maxHlRating | Float | 否 | 头部Listing前N名商品最高评分值 |
| minHlBsr | Integer | 否 | 头部Listing前N名商品最低BSR |
| maxHlBsr | Integer | 否 | 头部Listing前N名商品最高BSR |
| order | Object | 否 | 排序 |
| └field | String | 否 | 排序字段 |
| └desc | boolean | 否 | true为降序false为升序 |
| page | Integer | 否 | 页码，从1开始 |
| size | Integer | 否 | 每页条数 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| nodeIdPath | String | 节点ID路径 |
| nodeLabelPath | String | 节点名称路径 |
| nodeLabelLocale | String | 节点名称翻译 |
| countryCode | String | 国家二简码 |
| currency | String | 货币类型 |
| totalProducts | Integer | 商品总数 |
| brands | Integer | 品牌数 |
| sellers | Integer | 卖家数 |
| avgBsr | Integer | 平均BSR |
| avgVolume | Float | 平均体积(in³) |
| avgWeight | Float | 平均重量(pound) |
| avgProfit | Float | 平均利润率 |
| avgUnits | Integer | 月均销量 |
| avgRevenue | Float | 月均销售额 |
| avgPrice | Float | 平均价格 |
| avgRatings | Integer | 平均评分数 |
| avgRating | Float | 平均星级 |
| avgSellers | Float | 平均卖家数 |
| newProducts | Integer | 新品数量 |
| newProductProportion | Float | 新品数量占比 |
| newAvgPrice | Float | 新品平均价格 |
| newAvgRatings | Integer | 新品平均评分数 |
| newAvgRating | Float | 新品平均星级 |
| newAvgUnits | Integer | 新品月均销量 |
| newAvgRevenue | Float | 新品月均销售额 |
| hlProducts | Integer | 头部Listing前N名商品样本数 |
| hlAvgUnits | Integer | 头部Listing前N名商品月均销量 |
| hlAvgRevenue | Float | 头部Listing前N名商品月均销售额 |
| hlAvgPrice | Float | 头部Listing前N名商品平均价格 |
| hlAvgRatings | Integer | 头部Listing前N名商品平均评论数 |
| hlAvgRating | Float | 头部Listing前N名商品平均星级 |
| hlAvgBsr | Integer | 头部Listing前N名商品平均BSR |

---

## 20. 选市场-统计

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/market/statistics`
- **MCP Code**：market_research_statistics
- **功能说明**：查询类目下的统计数据

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 站点编码，见附录-市场编码表 |
| month | String | 否 | 筛选日期,默认最近30天，见附录-市场周期表 |
| topN | Integer | 否 | 头部Listing数量 |
| newProduct | Integer | 否 | 新品定义 |
| nodeIdPath | String | 是 | 节点id路径字符串 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| marketplace | String | 市场标志 |
| currency | String | 该市场的货币类型 |
| nodeIdPath | String | 节点ID路径 |
| nodeLabelPath | String | 节点名称路径 |
| nodeLabelLocale | String | 节点名称翻译 |
| countryCode | String | 国家二简码 |
| totalProducts | Integer | 商品总数 |
| products | Integer | 样品商品数 |
| brands | Integer | 品牌数 |
| sellers | Integer | 卖家数 |
| avgBsr | Integer | 平均BSR |
| baseAvgVolume | Float | 平均体积(cm³) |
| avgVolume | Float | 平均体积(in³) |
| baseAvgWeight | Float | 平均重量(g) |
| avgWeight | Float | 平均重量(pound) |
| avgProfit | Float | 平均利润率 |
| avgUnits | Integer | 月均销量 |
| avgRevenue | Float | 月均销售额 |
| avgPrice | Float | 平均价格 |
| avgRatingsCv | Integer | 月评论平均增长数 |
| avgRatings | Integer | 平均评分数 |
| avgRating | Float | 平均星级 |
| avgSellers | Float | 平均卖家数 |
| hlProducts | Integer | 头部Listing前N名商品样本数 |
| hlAvgBsr | Integer | 头部Listing前N名商品平均BSR |
| hlAvgUnits | Integer | 头部Listing前N名商品月均销量 |
| hlAvgRevenue | Float | 头部Listing前N名商品月均销售额 |
| hlAvgPrice | Float | 头部Listing前N名商品平均价格 |
| hlAvgRatingsCv | Integer | 头部Listing前N名商品月评论平均增长数 |
| hlAvgRatings | Integer | 头部Listing前N名商品平均评论数 |
| hlAvgRating | Float | 头部Listing前N名商品平均星级 |
| newProducts | Integer | 新品数量 |
| newProductProportion | Float | 新品数量占比 |
| newAvgPrice | Float | 新品平均价格 |
| newAvgRatings | Integer | 新品平均评分数 |
| minNewRatings | Integer | 最低新品评分数 |
| maxNewRatings | Integer | 最高新品评分数 |
| newAvgRating | Float | 新品平均星级 |
| newAvgUnits | Integer | 新品月均销量 |
| newAvgRevenue | Float | 新品月均销售额 |
| firstShelfDate | String | 商品首次上架日期 |
| lastShelfDate | String | 商品最新上架日期 |

---

## 21. 选市场-商品集中度

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/market/goods`
- **MCP Code**：market_product_concentration
- **功能说明**：商品集中度分析

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 站点编码，见附录-市场编码表 |
| month | String | 否 | 筛选日期,默认最近30天，见附录-市场周期表 |
| asins | List | 否 | 过滤asin |
| topN | Integer | 否 | 头部Listing数量 |
| newProduct | Integer | 否 | 新品定义 |
| nodeIdPath | String | 是 | 节点id路径字符串 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| title | String | 标题 |
| asin | String | asin |
| asinUrl | String | asin链接 |
| imageUrl | String | 图片链接 |
| ranking | Integer | 排名 |
| brand | String | 品牌 |
| sellerName | String | 卖家名称 |
| sellerType | String | 卖家类型 |
| price | Float | 价格 |
| shelfDate | String | 上架时间 |
| ratings | Integer | 评分数 |
| reviews | Integer | 评论数 |
| rating | Float | 评论值 |
| newFlag | Integer | 是否新品，1新品，0非新品 |
| totalUnits | Integer | 总销量 |
| totalRevenue | Float | 总销额 |
| totalUnitsRatio | Float | 总销量占比 |
| totalRevenueRatio | Float | 总销额占比 |

---

## 22. 选市场-品牌集中度

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/market/brand`
- **MCP Code**：market_brand_concentration
- **功能说明**：品牌集中度分析

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 站点编码，见附录-市场编码表 |
| month | String | 否 | 筛选日期,默认最近30天，见附录-市场周期表 |
| topN | Integer | 否 | 头部Listing数量 |
| newProduct | Integer | 否 | 新品定义 |
| nodeIdPath | String | 是 | 节点id路径字符串 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| brand | String | 品牌名称 |
| ranking | Integer | 排名 |
| asins | List | 包含的商品ASIN集合 |
| products | Integer | 商品数量，包含新品 |
| newProducts | Integer | 新品数量 |
| newUnits | Integer | 新品销量 |
| newRevenue | Float | 新品销售额 |
| newUnitsRatio | Float | 新品销量占比 |
| newRevenueRatio | Float | 新品销售额占比 |
| avgPrice | Float | 平均价格 |
| ratings | Integer | 评分数 |
| rating | Float | 评分值 |
| reviews | Integer | 评论数 |
| totalUnits | Integer | 总销量 |
| totalRevenue | Float | 总销额 |
| totalUnitsRatio | Float | 总销量占比 |
| totalRevenueRatio | Float | 总销额占比 |

---

## 23. 选市场-卖家集中度

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/market/seller`
- **MCP Code**：market_seller_concentration
- **功能说明**：卖家集中度分析

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 站点编码，见附录-市场编码表 |
| month | String | 否 | 筛选日期,默认最近30天，见附录-市场周期表 |
| topN | Integer | 否 | 头部Listing数量 |
| newProduct | Integer | 否 | 新品定义 |
| nodeIdPath | String | 是 | 节点id路径字符串 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| name | String | 卖家名称 |
| ranking | Integer | 排名 |
| asinSet | List | 包含的商品ASIN集合 |
| products | Integer | 商品数量，包含新品 |
| newProducts | Integer | 新品数量 |
| newUnits | Integer | 新品销量 |
| newRevenue | Float | 新品销售额 |
| newUnitsRatio | Float | 新品销量占比 |
| newRevenueRatio | Float | 新品销售额占比 |
| avgPrice | Float | 平均价格 |
| ratings | Integer | 评分数 |
| rating | Float | 评分值 |
| reviews | Integer | 评论数 |
| totalUnits | Integer | 总销量 |
| totalRevenue | Float | 总销额 |
| totalUnitsRatio | Float | 总销量占比 |
| totalRevenueRatio | Float | 总销额占比 |

---

## 24. 选市场-卖家类型分布

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/market/seller/type`
- **MCP Code**：market_seller_type_concentration
- **功能说明**：卖家类型分布分析

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 站点编码，见附录-市场编码表 |
| month | String | 否 | 筛选日期,默认最近30天，见附录-市场周期表 |
| topN | Integer | 否 | 头部Listing数量 |
| newProduct | Integer | 否 | 新品定义 |
| nodeIdPath | String | 是 | 节点id路径字符串 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| label | String | 类型说明 |
| asinNum | Integer | ASIN数量 |
| asinRatio | Float | ASIN数量占比 |
| units | Integer | 月销量 |
| unitsRatio | Float | 月销量占比 |
| ratings | Integer | 评分数 |
| rating | Float | 评分值 |
| productNum | Integer | 商品总数 |

---

## 25. 选市场-卖家所属地分布

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/market/seller/location`
- **MCP Code**：market_seller_country_distribution
- **功能说明**：卖家所属地分布分析

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 站点编码，见附录-市场编码表 |
| month | String | 否 | 筛选日期,默认最近30天，见附录-市场周期表 |
| topN | Integer | 否 | 头部Listing数量 |
| newProduct | Integer | 否 | 新品定义 |
| nodeIdPath | String | 是 | 节点id路径字符串 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| label | String | 类型说明 |
| country | String | 国家 |
| asins | List | 包含的asin列表 |
| products | Integer | 产品数 |
| revenue | Float | 销售额 |
| units | Integer | 销量 |
| unitsRatio | Float | 销量占比 |

---

## 26. 选市场-商品需求趋势

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/market/performance`
- **MCP Code**：market_product_demand_trend
- **功能说明**：商品需求趋势

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 市场id，见附录-市场编码表 |
| month | String | 否 | 筛选日期,默认最近30天，最早查询时间为2021年7月份，见附录-市场周期表 |
| topN | Integer | 否 | 头部Listing数量 |
| newProduct | Integer | 否 | 新品定义 |
| nodeIdPath | String | 是 | 节点id路径字符串 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| asinCount | String | asin数量 |
| returnRatio | String | 退货率，百分比 |
| searchToPurchaseRatio | List | 搜索购买比，千分比 |
| avgReturnRatio | Integer | 类目平均退货率，百分比 |
| avgSearchToPurchaseRatio | Float | 类目平均搜索购买比，千分比 |
| items | List | 月浏览趋势 |
| └date | String | 时间，yyyy-MM-dd格式 |
| └glanceViews | Integer | 浏览量 |

---

## 27. 选市场-上架时间分布

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/market/shelf/time`
- **MCP Code**：market_listing_date_distribution
- **功能说明**：上架时间分布

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 站点编码，见附录-市场编码表 |
| month | String | 否 | 筛选日期,默认最近30天，见附录-市场周期表 |
| topN | Integer | 否 | 头部Listing数量 |
| newProduct | Integer | 否 | 新品定义 |
| nodeIdPath | String | 是 | 节点id路径字符串 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| label | String | 时间区间 |
| products | Integer | 商品数量 |
| proportion | Float | 占比 |

---

## 28. 选市场-价格分布

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/market/price`
- **MCP Code**：market_price_distribution
- **功能说明**：价格分布分析

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 站点编码，见附录-市场编码表 |
| month | String | 否 | 筛选日期,默认最近30天，见附录-市场周期表 |
| topN | Integer | 否 | 头部Listing数量 |
| newProduct | Integer | 否 | 新品定义 |
| nodeIdPath | String | 是 | 节点id路径字符串 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| label | String | 价格区间 |
| products | Integer | 商品数量 |
| proportion | Float | 占比 |

---

## 29. 选市场-评分分布

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/market/rating`
- **MCP Code**：market_rating_distribution
- **功能说明**：评分分布分析

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 站点编码，见附录-市场编码表 |
| month | String | 否 | 筛选日期,默认最近30天，见附录-市场周期表 |
| topN | Integer | 否 | 头部Listing数量 |
| newProduct | Integer | 否 | 新品定义 |
| nodeIdPath | String | 是 | 节点id路径字符串 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| label | String | 评分区间 |
| products | Integer | 商品数量 |
| proportion | Float | 占比 |

---

## 30. 选市场-评论数分布

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/market/reviews`
- **MCP Code**：market_reviews_distribution
- **功能说明**：评论数分布分析

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 站点编码，见附录-市场编码表 |
| month | String | 否 | 筛选日期,默认最近30天，见附录-市场周期表 |
| topN | Integer | 否 | 头部Listing数量 |
| newProduct | Integer | 否 | 新品定义 |
| nodeIdPath | String | 是 | 节点id路径字符串 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| label | String | 评论数区间 |
| products | Integer | 商品数量 |
| proportion | Float | 占比 |

---

## 31. 选市场-BSR分布

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/market/bsr`
- **MCP Code**：market_bsr_distribution
- **功能说明**：BSR分布分析

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 站点编码，见附录-市场编码表 |
| month | String | 否 | 筛选日期,默认最近30天，见附录-市场周期表 |
| topN | Integer | 否 | 头部Listing数量 |
| newProduct | Integer | 否 | 新品定义 |
| nodeIdPath | String | 是 | 节点id路径字符串 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| label | String | BSR区间 |
| products | Integer | 商品数量 |
| proportion | Float | 占比 |

---

## 32. 选市场-销量分布

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/market/units`
- **MCP Code**：market_units_distribution
- **功能说明**：销量分布分析

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 站点编码，见附录-市场编码表 |
| month | String | 否 | 筛选日期,默认最近30天，见附录-市场周期表 |
| topN | Integer | 否 | 头部Listing数量 |
| newProduct | Integer | 否 | 新品定义 |
| nodeIdPath | String | 是 | 节点id路径字符串 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| label | String | 销量区间 |
| products | Integer | 商品数量 |
| proportion | Float | 占比 |

---

## 33. 选市场-销售额分布

- **HTTP方法**：POST
- **请求路径**：`https://api.sellersprite.com/v1/market/revenue`
- **MCP Code**：market_revenue_distribution
- **功能说明**：销售额分布分析

### 请求参数

| 参数 | 类型 | 必填 | 说明 |
|------|------|------|------|
| marketplace | String | 是 | 站点编码，见附录-市场编码表 |
| month | String | 否 | 筛选日期,默认最近30天，见附录-市场周期表 |
| topN | Integer | 否 | 头部Listing数量 |
| newProduct | Integer | 否 | 新品定义 |
| nodeIdPath | String | 是 | 节点id路径字符串 |

### 返回字段

| 参数 | 类型 | 说明 |
|------|------|------|
| label | String | 销售额区间 |
| products | Integer | 商品数量 |
| proportion | Float | 占比 |

---

## 附录

---

## 附录1. 市场编码表

| 编码 | 市场 |
|------|------|
| US | 美国 |
| UK | 英国 |
| DE | 德国 |
| FR | 法国 |
| IT | 意大利 |
| ES | 西班牙 |
| JP | 日本 |
| CA | 加拿大 |
| AU | 澳大利亚 |
| IN | 印度 |
| MX | 墨西哥 |
| BR | 巴西 |
| AE | 阿联酋 |
| SA | 沙特 |
| NL | 荷兰 |
| SE | 瑞典 |
| PL | 波兰 |
| TR | 土耳其 |
| EG | 埃及 |
| BE | 比利时 |
| SG | 新加坡 |

---

## 附录2. 排序字段表

| 字段 | 说明 |
|------|------|
| total_units | 月销量 |
| total_revenue | 月销售额 |
| price | 价格 |
| ratings | 评分数 |
| rating | 评分值 |
| bsr | BSR排名 |
| available_date | 上架时间 |
| variations | 变体数 |
| sellers | 卖家数 |
| profit | 利润率 |
| lqs | Listing质量得分 |
| ratings_cv | 月新增评分数 |
| bsr_cr | BSR增长率 |
| units_gr | 月销量增长率 |
| revenue_gr | 月销售额增长率 |

---

## 附录3. 市场周期表

| 周期 | 说明 |
|------|------|
| 最近30天 | 不传month参数或传空字符串 |
| yyyyMM | 指定月份，如202507表示2025年7月 |

---

## 附录4. 流量词类型表

| 类型 | 说明 |
|------|------|
| ns | 自然搜索词 |
| ac | AC推荐词 |
| er | ER推荐词 |
| fs | 4星推荐词 |
| hr | HR推荐词 |
| sb | 品牌广告词 |
| sv | 视频广告词 |
| ad | SP广告词 |

---

## 附录5. 卖家国籍表

| 编码 | 国家 |
|------|------|
| US | 美国 |
| UK | 英国 |
| DE | 德国 |
| FR | 法国 |
| IT | 意大利 |
| ES | 西班牙 |
| JP | 日本 |
| CN | 中国 |
| CA | 加拿大 |
| AU | 澳大利亚 |
| IN | 印度 |
| MX | 墨西哥 |
| BR | 巴西 |
| AE | 阿联酋 |
| SA | 沙特 |
| NL | 荷兰 |
| SE | 瑞典 |
| PL | 波兰 |
| TR | 土耳其 |
| EG | 埃及 |
| BE | 比利时 |
| SG | 新加坡 |

---

## 附录6. 关联流量类型表

| 类型 | 说明 |
|------|------|
| FREQUENTLY_Bought_Together | 经常一起购买 |
| PRODUCTS_RELATED_TO_THIS_ITEM | 相关商品 |
| CUSTOMERS_WHO_VIEWED_THIS_ITEM_ALSO_VIEWED | 看了又看 |
| WHAT_OTHER_ITEMS_CUSTOMERS_BUY_AFTER_VIEWING_THIS_ITEM | 买了又买 |
| SP_Sponsored_products_related_to_this_item | SP广告相关商品 |
| SP_Sponsored_products_based_on_your_browsing_history | SP广告浏览历史 |
| SP_Sponsored_products_based_on_your_purchase_history | SP广告购买历史 |
| BRAND_Sponsored_products_from_this_brand | 品牌广告 |
| SV_Sponsored_products_related_to_this_item | 视频广告相关商品 |

---

## 附录7. 流量占比类型表

| 类型 | 说明 |
|------|------|
| HIGH | 高流量占比 |
| MEDIUM | 中流量占比 |
| LOW | 低流量占比 |

---

## 附录8. 流量转化类型表

| 类型 | 说明 |
|------|------|
| E | 转化优质词 |
| S | 转化平稳词 |
| L | 转化流失词 |
| I | 无效曝光词 |

---

## 附录9. 搜索模式表

| 模式 | 说明 |
|------|------|
| 1 | 热门市场 |
| 2 | 异动市场 |
| 3 | 持续增长市场 |
| 4 | 快速飙升市场 |
| 5 | 潜力市场 |
| 6 | 尾市场 |

---