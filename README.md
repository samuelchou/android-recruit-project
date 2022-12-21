# 作業完成記錄

- 作者：Samuel Chou
- 時間：2022/12/19-12/21
- 相容性：SDK 21-32 (Android 5 - Android 12L)

預覽圖

![](demo-1.png)

## 功能介紹

1. 提供一個 App, 可以載入並顯示課程列表。
2. 專案採用 MVVM, 並引入了依賴注入做處理。
3. 針對資料解析進行了單元測試。

## 耗時統計

總共花費約 ~7.5h.

- 瞭解與整理規格： ~0.5h
- 初始化與架構： ~2.5h
- 資料解析： ~0.75h
- Repo 架設： ~0.25h
- 版面繪製： ~2.5h
- 規格更新與檢查： ~1h

## 技術規格說明

1. 框架：MVVM 搭配依賴注入 (Hilt)
2. 解析資料：KotlinX Serialization, Java 8 LocalDateTime
3. 視圖元件：RecyclerView
4. 圖片載入：Glide

---

# 原先作業指派內容

請使用 Kotlin 實作一個 app 首頁課程列表畫面，並寫文件或註解來解釋你的設計考量。除了指定需求外，你可以自由設計 model 和 UI 來提供更好的體驗。

## 技術規定
- Deployment Target 為 Android 12。
- 可使用第三方 library。
- 請寫文件或註解來解釋你的設計考量。

## 需求
請實作一個在 app 首頁看到的課程列表，需求如下：

#### 資料
- 請設計一個的 Data Loader 的抽象層來提供課程資料。
- 請用專案中提供的 json file 實作上述 Data Loader 的一個實例。

#### UI 設計
- 依照課程當前狀態，顯示不同的標籤
- 課程標題至多兩行
- 本題目不用在意卡片尺寸、顏色、間距等細節，請將重點放在如何排版。（你仍然可以盡量符合示意圖）
<img width="300" alt="CleanShot 2021-12-09 at 10 59 30@2x" src="https://user-images.githubusercontent.com/76472179/145350022-b4624fe0-2612-4fdb-950c-da6898ca4166.png">

## 提交

- 請下載或 fork Hahow Android Engineer 面試題目初始專案。
- 請將成果上傳至 GitHub 並直接提供 repo 連結。
