## 環境構築
### java1.8にアップデート

#### インストール
```sudo yum install java-1.8.0-openjdk.x86_64```

#### アップデート
```sudo alternatives --config java```

⇒java1.8を選択

 
### Gradleのインストール(Win10向け)

http://qiita.com/quwahara/items/9c4e5fbda421cfcb09ad

 
### sourcetreeのインストール

http://tracpath.com/bootcamp/learning_git_sourcetree.html
 
 
## 実行

### ローカル環境
#### クローンしたフォルダへ移動

```$cd XXXX```

#### ビルド

```gradle build```

#### 実行

```gradle bootRun```

#### EC2サーバ
1.「gradle.bat」のあるフォルダへ移動

```$cd XXXX```

2.実行

```./gradle.bat```
