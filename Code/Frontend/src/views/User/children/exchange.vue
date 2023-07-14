<template>
<div id="wrapper">
  <y-shelf title="基于 OpenAI 的 ChatGPT 自然语言模型人工智能对话">
  <template #right>
  <y-button text="设置" style="margin: 0" @btnClick="clickConfig()">
  </y-button>
  </template>

  <template #content>
    <div class="message" ref="chatListDom">
    <div>
      <div class="a" v-for="item of messageList.filter((v) => v.role !== 'system')">
      <div class="tishu">
        <div class="b">{{ roleAlias[item.role] }}: </div>
        <Copy :content="item.content"></Copy>
      </div>
      <div>
        <pre
            class="text-sm text-slate-600 whitespace-pre-wrap leading-relaxed"
            v-if="item.content"
            >{{ item.content.replace(/^\n\n/, "") }}</pre
          >
          <Loding v-else />
      </div>



      </div>
    </div>
    </div>


    <div class="setting">
      <div class="api">请输入 API Key：</div>
      <div class="flex">
      <input
      class="input"
      :type="isConfig ? 'password' : 'text'"
      :placeholder="isConfig ? 'sk-xxxxxxxxxx' : '请输入'"
      v-model="messageContent"
      @keydown.enter="isTalking || sendOrSave()"
      />
      <button class="btn" :disabled="isTalking" @click="sendOrSave()">
      {{ isConfig ? "保存" : "发送" }}
      </button>
    </div>
    </div>
    <div class="prompt_content">
    <div class="dialog">
    </div>
    </div>
  </template>
      <!-- <template #content> -->
      <!-- <div class="flex flex-col h-screen">
          <div class="flex flex-nowrap fixed w-full items-baseline top-0 px-6 py-4 bg-gray-100">
          </div>
          <div class="flex-1 mx-2 mt-20 mb-2" ref="chatListDom">
              <div class="group flex flex-col px-4 py-3 hover:bg-slate-100 rounded-lg"
                  v-for="item of messageList.filter((v) => v.role !== 'system')"
              >
                  <div class="group flex flex-col px-4 py-3 hover:bg-slate-100 rounded-lg">
                      <div class="flex justify-between items-center mb-2">
                          <div class="font-bold">{{ roleAlias[item.role] }}：</div>
                          <Copy class="invisible group-hover:visible" :content="item.content" />
                      </div>
                      <div>
                          <pre
                              class="text-sm text-slate-600 whitespace-pre-wrap leading-relaxed"
                              v-if="item.content"
                              >{{ item.content.replace(/^\n\n/, "") }}</pre
                          >
                          <Loding v-else />
                      </div>
                  </div>
              </div>
          </div>

          <div class="sticky bottom-0 w-full p-6 pb-8 bg-gray-100">
              <div class="-mt-2 mb-2 text-sm text-gray-500" v-if="isConfig">
                  请输入 API Key：
              </div>
              <div class="flex">
                  <input
                  class="input"
                  :type="isConfig ? 'password' : 'text'"
                  :placeholder="isConfig ? 'sk-xxxxxxxxxx' : '请输入'"
                  v-model="messageContent"
                  @keydown.enter="isTalking || sendOrSave()"
                  />
                  <button class="btn" :disabled="isTalking" @click="sendOrSave()">
                  {{ isConfig ? "保存" : "发送" }}
                  </button>
              </div>
          </div> -->
      <!-- </div>
      </template>
      </y-shelf> -->
  </y-shelf>
      <!-- </div> -->
</div>
</template>

<script>
const CryptoJS = require('crypto-js');
import YShelf from "../../../components/shelf.vue";
import YButton from "../../../components/YButton.vue";
import {ref,watch, nextTick, onMounted} from "vue";
import {chat} from "../../../api/chat";
import Loding from "@/components/loading.vue";
import Copy from "@/components/copy.vue";




export default {
    components:{
        YShelf,
        YButton
    },
    setup() {
        let count = ref(0);
        let isConfig = ref(true);
        let isTalking = ref(false);
        let messageContent = ref("");
        let apiKey = "";
        const chatListDom = ref<HTMLDivElement | null>(null);
        const messageList = [
  {
    role: "system",
    content: "你是 ChatGPT，OpenAI 训练的大型语言模型，尽可能简洁地回答。",
  },
  {
    role: "assistant",
    content: `你好，我是AI语言模型，我可以提供一些常用服务和信息。`,
  }
        ]
        const switchConfigStatus = () => (isConfig.value = !isConfig.value);
        const sendChatMessage = async (content = messageContent.value) => {
  try {
    isTalking.value = true;
    if (messageList.value.length === 2) {
      messageList.value.pop();
    }
    messageList.value.push({ role: "user", content });
    clearMessageContent();
    messageList.value.push({ role: "assistant", content: "" });

    const { body, status } = await chat(messageList.value, getAPIKey());
    if (body) {
      const reader = body.getReader();
      await readStream(reader, status);
    }
  } catch (error) {
    appendLastMessageContent(error);
  } finally {
    isTalking.value = false;
  }
        };

        const readStream = async (
  reader,
  status
) => {
  let partialLine = '';

  while (true) {
    // eslint-disable-next-line no-await-in-loop
    const { value, done } = await reader.read();
    if (done) break;

    const decodedText = decoder.decode(value, { stream: true });

    if(status === 400){
      const json = JSON.parse(decodedText); // start with "data: "
      const content = json.error.message ?? decodedText;
      appendLastMessageContent(content);
      return;
    }

    const chunk = partialLine + decodedText;
    const newLines = chunk.split(/\r?\n/);

    partialLine = newLines.pop() ?? '';

    for (const line of newLines) {
      if (line.length === 0) continue; // ignore empty message
      if (line.startsWith(':')) continue; // ignore sse comment message
      if (line === 'data: [DONE]') return; //

      const json = JSON.parse(line.substring(6)); // start with "data: "
      const content =
        status === 200 ? json.choices[0].delta.content ?? "" : json.error.message;
      appendLastMessageContent(content);
    }
  }
        };
        const appendLastMessageContent = (content) =>(messageList.value[messageList.value.length - 1].content += content);
        const getSecretKey = () => "lianginx";
        const saveAPIKey = (apiKey) => {
        if (apiKey.slice(0, 3) !== "sk-" || apiKey.length !== 51) {
            alert("API Key 错误，请检查后重新输入！");
            return false;
        }
        const aesAPIKey = cryptoJS.AES.encrypt(apiKey, getSecretKey()).toString();
        localStorage.setItem("apiKey", aesAPIKey);
        return true;
        };
        const clearMessageContent = () => (messageContent.value = "");
        const scrollToBottom = () => {
        if (!chatListDom.value) return;
        scrollTo(0, chatListDom.value.scrollHeight);
        };
        const decoder = new TextDecoder("utf-8");
        const roleAlias = { user: "ME", assistant: "ChatGPT", system: "System" };
        const clickConfig = () => {
            if (!isConfig.value) {
                messageContent.value = getAPIKey();
            } else {
                clearMessageContent();
            }
            switchConfigStatus();
        }
        const sendOrSave = () => {
  if (!messageContent.value.length) return;
  if (isConfig.value) {
    if (saveAPIKey(messageContent.value.trim())) {
      switchConfigStatus();
    }
    clearMessageContent();
  } else {
    sendChatMessage();
  }
        }
        const getAPIKey = () => {
  if (apiKey) return apiKey;
  const aesAPIKey = localStorage.getItem("apiKey") ?? "";
  apiKey = cryptoJS.AES.decrypt(aesAPIKey, getSecretKey()).toString(
    cryptoJS.enc.Utf8
  );
  return apiKey;
        }
        onMounted(() => {
        if (getAPIKey()) {
            switchConfigStatus();
        }
        });
        watch(messageList.value, () => nextTick(() => scrollToBottom()));
        return{
                clickConfig,
                sendOrSave,
                getAPIKey,
                isConfig,
                isTalking,
                messageContent,
                apiKey,
                count,
                roleAlias,
                messageList
            }
    }
}
</script>

<style lang="less" scoped>
#wrapper .content
.support {
    line-height: 2em;
    font-size: 22px;
    color: #999;
    // background-color: rgb(156, 6, 6);
  }
.pre {
  font-family: -apple-system, "Noto Sans", "Helvetica Neue", Helvetica,
    "Nimbus Sans L", Arial, "Liberation Sans", "PingFang SC", "Hiragino Sans GB",
    "Noto Sans CJK SC", "Source Han Sans SC", "Source Han Sans CN",
    "Microsoft YaHei", "Wenquanyi Micro Hei", "WenQuanYi Zen Hei", "ST Heiti",
    "SimHei", "WenQuanYi Zen Hei Sharp", sans-serif;
}
.setting{
  display: flex;
}
.prompt_content{
  background-color: rgb(248, 243, 243);
  height: 400px;
  width: auto;
  padding: auto;
  margin: auto;
}
</style>