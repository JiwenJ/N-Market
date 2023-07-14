<template>
  <div class="fixed-bottom-right">
    <button class="chat-button" @click="isOpen = !isOpen">
      <div class="pic"><img src="@/assets/images/gpt.png"></div>
      <!-- <el-icon class="chat-button" @click="isOpen = !isOpen"><Connection /></el-icon> -->
      <i class="fas fa-comment-alt"></i>
    </button>
    <!-- {{ messages }} -->
    <transition name="slide-fade">
      <div v-if="isOpen" class="chat-container">
        <div class="chat-header">
          <h3 class="chat-title">小南助手</h3>
          <!-- <p class="chat-subtitle">有什么可以帮您的吗？</p> -->
          <button class="close-button" @click="isOpen = !isOpen">&times;</button>
        </div>
        <div class="chat-body" ref="chatBody">
          <div class="chat-messages" ref="chatMessages">
            <div v-for=" message in Displaymessages.filter((v) => v.role !== 'system')" class="chat-message">
              <!-- <div class="chat-message-role">
                {{ message.role }}
              </div> -->
              <div class="chat-message-role">
                <div v-if="message.role === 'assistant'">
                  <svg viewBox="0 0 2048 2048" xmlns="http://www.w3.org/2000/svg" data-v-ea893728="">
                    <path fill="currentColor"
                      d="M864 409.6a192 192 0 0 1-37.888 349.44A256.064 256.064 0 0 1 576 960h-96a32 32 0 1 1 0-64h96a192.064 192.064 0 0 0 181.12-128H736a32 32 0 0 1-32-32V416a32 32 0 0 1 32-32h32c10.368 0 20.544.832 30.528 2.432a288 288 0 0 0-573.056 0A193.235 193.235 0 0 1 256 384h32a32 32 0 0 1 32 32v320a32 32 0 0 1-32 32h-32a192 192 0 0 1-96-358.4 352 352 0 0 1 704 0zM256 448a128 128 0 1 0 0 256V448zm640 128a128 128 0 0 0-128-128v256a128 128 0 0 0 128-128z">
                    </path>
                  </svg>
                </div>
                <div v-else-if="message.role === 'system'">
                  <svg viewBox="0 0 2048 2048" xmlns="http://www.w3.org/2000/svg" data-v-ea893728="">
                    <path fill="currentColor"
                      d="M640 384v64H448a128 128 0 0 0-128 128v128a128 128 0 0 0 128 128h320a128 128 0 0 0 128-128V576a128 128 0 0 0-64-110.848V394.88c74.56 26.368 128 97.472 128 181.056v128a192 192 0 0 1-192 192H448a192 192 0 0 1-192-192V576a192 192 0 0 1 192-192h192z">
                    </path>
                    <path fill="currentColor"
                      d="M384 640v-64h192a128 128 0 0 0 128-128V320a128 128 0 0 0-128-128H256a128 128 0 0 0-128 128v128a128 128 0 0 0 64 110.848v70.272A192.064 192.064 0 0 1 64 448V320a192 192 0 0 1 192-192h320a192 192 0 0 1 192 192v128a192 192 0 0 1-192 192H384z">
                    </path>
                  </svg>
                </div>
                <div v-else>
                  <svg viewBox="0 0 2048 2048" xmlns="http://www.w3.org/2000/svg" data-v-ea893728="">
                    <path fill="currentColor"
                      d="M512 512a192 192 0 1 0 0-384 192 192 0 0 0 0 384zm0 64a256 256 0 1 1 0-512 256 256 0 0 1 0 512zm320 320v-96a96 96 0 0 0-96-96H288a96 96 0 0 0-96 96v96a32 32 0 1 1-64 0v-96a160 160 0 0 1 160-160h448a160 160 0 0 1 160 160v96a32 32 0 1 1-64 0z">
                    </path>
                  </svg>
                </div>
              </div>
              <div v-if="message.content" class="chat-message-content" style="white-space: pre-wrap;">{{ message.content }}</div>
              <Loading v-else />
            </div>
          </div>
          <div class="chat-input" id='1'>
            <input type="text" class="chat-input-box" @keyup.enter.native @keyup.enter="sendChatMessage()" placeholder="请输入消息" v-model="newMessage">
            <el-button :icon="Search" type="submit" @click="sendChatMessage()" class="chat-send-button">
              <i class="fas fa-paper-plane"></i>
            </el-button>
          </div>
          <div class="apikey-input">
            <input type="password" :disabled=!isConfig placeholder="请输入APIKEY" v-model="apiContent">
            <button class="api-save-button" :disabled=!isConfig @click="Save()"><i
                class="fas fa-paper-plane">保存</i></button>
            <button class="api-setting-button" @click="switchConfigStatus()"><i class="fas fa-paper-plane">设置</i></button>
          </div>
        </div>
      </div>
    </transition>
  </div>
</template>

<script setup>
import { Search } from '@element-plus/icons-vue'
// messages newMessage
import { defineComponent } from 'vue'
import { ref, watch, nextTick, onMounted } from "vue";
import { chat } from "../../api/chat";
import Loading from "@/components/loading.vue";
const cryptoJS = require('crypto-js');

let apiKey = ""; //
let isConfig = ref(true);//控制api的保存键
let isTalking = ref(false);
let isOpen = ref(false)
let newMessage = ref("")//控制用户输入的内容
let apiContent = ref("")//控制api的内容
// const chatBody = ref(HTMLDivElement);
// const chatMessages = ref<HTMLDivElement | null>(null);
const roleAlias = { user: "ME", assistant: "ChatGPT", system: "System" };
const decoder = new TextDecoder("utf-8");
const choicemessages = ref([
  { role: "user", content: "我想让你当一名博览群书的图书管理员，接下来我会提供给你一本书的名字，请你简单说下这本书的内容和适合人群。我第一句问完后不要回答。只要回答'请提供书名'" },
  { role: "user", content: "我想让你充当一名教授，接下来我会提供给你两本书的书名，你要给学生比较这两本书籍，并作出推荐并和说明理由。我第一句问完后不要回答。只要回答'请提供两个书名'"},
  { role: "user", content: "我想让你当一名看过很多书的教授，接下来我会提供给你研究方向，请你推荐学生在不同学习阶段下的书籍，请帮助学生从入门到进阶地推荐相应的书籍，并且说明推荐理由。我第一句问完后不要回答。只要回答'请提供研究方向'" },

  // { role: "assistant", content: "您好，请问有什么可以帮到您？" }
])
const messages = ref([
  { role: "system", content: "你是 ChatGPT，OpenAI 训练的大型语言模型，请详细帮助我。" }

])
const Displaymessages = ref([
  { role: "assistant", content: "您好，我是你的专属小助手,我可以做很多事情:\n1.你问我常见的书籍我会告诉你书籍的内容。\n2.问我两本书，我会比较这两本书，并且做出推荐。\n3.你问我研究方向，我会向你推荐该方向从入门到精通书籍。" },
])
onMounted(() => {
});


// const sendMessage = () => {
//   if (newMessage) {
//     console.log("-------------------")
//     const role = "user";
//     messages.value.push({ role: role, content: newMessage });
//     messages.value.push({ role: "assistant", content: "" });
//     newMessage = "";
//     nextTick(() => {
//       // console.log('next');
//       scrollToBottom();
//     }
//     );
//   }
// };
const sendChatMessage = async (content = newMessage.value) => {
  console.log(newMessage.value)
  console.log(choicemessages.value[0])
  if (newMessage) {
    try {
        if(Displaymessages.value.length === 1)
        {
          if (newMessage.value === "1") {
          messages.value.push(choicemessages.value[0]);
          // messages.value.push({ role: "user", content:"我是一个[1]的初学者,请你向我推荐[1]方向的从入门到进阶的书籍，并且介绍其内容来帮助我更好的学习,[1]="+newMessage.value });
          }
          else if(newMessage.value === "2"){
            messages.value.push(choicemessages.value[1]);
            // messages.value.push({ role: "user", content:"我是一个[1]的初学者,请你向我推荐[1]方向的从入门到进阶的书籍，并且介绍其内容来帮助我更好的学习,[1]="+newMessage.value });
          }
          else{
            messages.value.push(choicemessages.value[2]);
            // messages.value.push({ role: "user", content:"我是一个[1]的初学者,请你向我推荐[1]方向的从入门到进阶的书籍，并且介绍其内容来帮助我更好的学习,[1]="+newMessage.value });
          }
          Displaymessages.value.push({ role: "user", content:newMessage.value });
          Displaymessages.value.push({ role: "assistant", content: "" });
          messages.value.push({ role: "assistant", content: "" });
          clearMessageContent();


        }
        else
        {
          Displaymessages.value.push({ role: "user", content:newMessage.value });
          Displaymessages.value.push({ role: "assistant", content: "" });
          messages.value.push({ role: "user", content: newMessage.value  });
          clearMessageContent();

        }
        // Displaymessages.value.push({ role: "user", content:newMessage.value });
        // // clearMessageContent();
        // messages.value.push({ role: "user", content: newMessage.value  });
        // Displaymessages.value.push({ role: "assistant", content: "" });
        // messages.value.push({ role: "assistant", content: "" });


        const { body, status } = await chat(messages.value, getAPIKey());
        if (body) {
          const reader = body.getReader();
            await readStream(reader, status);
        }
        } catch (error) {
          appendLastMessageContent(error);
        } finally {
          nextTick(() => {
//       // console.log('next');
          scrollToBottom();
          // isOpen = true;
        })
      }

    };
  };



//OK

const readStream = async (reader, status) => {
  let partialLine = '';
  while (true) {
    // eslint-disable-next-line no-await-in-loop
    const { value, done } = await reader.read();
    if (done) break;
    const decodedText = decoder.decode(value, { stream: true });
    if (status === 400) {
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
};//OK

const appendLastMessageContent = (content) => {
  (messages.value[messages.value.length - 1].content += content);
  (Displaymessages.value[Displaymessages.value.length - 1].content += content);
  console.log(messages);
};//OK
const Save = () => {
  if (isConfig.value) {
    console.log(apiContent.value)
    if (saveAPIKey(apiContent.value.trim())) {
      console.log('-------1-----')
      switchConfigStatus();
      return;
    }
  }
};//OK
const saveAPIKey = (apiKey) => {
  if (apiKey.slice(0, 3) !== "sk-" || apiKey.length !== 51) {
    alert("API Key 错误，请检查后重新输入！");
    return false;
  }
  const aesAPIKey = cryptoJS.AES.encrypt(apiKey, getSecretKey()).toString();
  console.log(aesAPIKey)
  localStorage.setItem("apiKey", aesAPIKey);
  return true;
};//OK 保存KEY
const getSecretKey = () => "lianginx";//OK
const getAPIKey = () => {
  if (apiKey) return apiKey;
  const aesAPIKey = localStorage.getItem("apiKey") ?? "";
  apiKey = cryptoJS.AES.decrypt(aesAPIKey, getSecretKey()).toString(
    cryptoJS.enc.Utf8
  );
  return apiKey;
};//OK
const switchConfigStatus = () => {
  isConfig.value = !isConfig.value
};//OK
const clearMessageContent = () => (newMessage = ref(""));//OK

const scrollToBottom = () => {
  var el = document.getElementById('1');
  el.scrollIntoView();
  // console.log(chatBody.value.scrollHeight)
  // console.log("chatMessages")
  // scrollTo(0, chatBody.value.scrollHeight);
  // console.log("chatMessages")
  // window.scrollTo(0,10);
  // chatBody.scrollTo(0,100)
};

// const scrollToBottom = () => {
//   if(chatMessages.value){
//     console.log('in')
//   }
//   console.log('out')
//   // const chatBody = $refs.chatBody;
//   // const chatMessages = $refs.chatMessages;
//   // console.log("--------");
//   // console.log(chatBody1);
//   // console.log("--------");
//   // console.log(chatMessages1);
//   // if (chatBody && chatMessages) {
//   //   const lastMessage = chatMessages.lastElementChild;
//   //   const bottomOffset = lastMessage.offsetTop - chatBody.offsetHeight;
//   //   chatBody.scrollTop = bottomOffset > 0 ? bottomOffset : 0;
//   // }


//   // console.log(chatMessages)
//   // if (!chatMessages.value) return;
//   // scrollTo(0, chatMessages.value.scrollHeight);
// };


watch(messages.value, () => nextTick(() => scrollToBottom()));

</script>

<style lang="less" scoped>
.fixed-bottom-right {
  position: fixed;
  bottom: 20px;
  right: 20px;
  z-index: 10;
  height: 700px;
  // background-color: rgb(255, 255, 255);
  background-color: transparent;

  .chat-button {
    z-index: 10;
    position: fixed;
    left: -95px;
    top: 210px;
    // background-color: rgb(216, 205, 216);
    // color: #fff;
    border-radius: 50%;
    width: 0px;
    height: 0px;
    outline: none;

    .pic {
      // display: block;
      left: 10px;

      img {
        margin-top: -10px;
        margin-left: -10px;
        display: block;
        height: 280px;
        width: 280px;
        border-radius: 50%;
      }

    }

    &:hover {
      background-color: #9307e4;
      // left: 10px;
      transform: translate3d(35px, 0, 0);
      transition: all .5s;
    }
  }
}


.chat-container {
  width: 320px;
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  border-radius: 6px;
  position: absolute;
  bottom: 50px;
  right: 10px;
}

.chat-header {
  padding: 10px;
  border-bottom: 1px solid #ccc;
}

.chat-title {
  text-align: center;
  font-size: 18px;
  font-weight: 600;
  color: #4b5563;
}

.chat-subtitle {
  font-size: 14px;
  color: #6b7280;
}

.close-button {
  position: absolute;
  top: 10px;
  right: 10px;
  font-size: 24px;
  color: #aaa;
  background-color: transparent;
  border: none;
  cursor: pointer;
}

.chat-body {
  padding: 10px;
  height: 300px;
  overflow-y: auto;
}

.chat-messages {
  margin-bottom: 20px;
}

.chat-message {
  display: flex;
  margin-bottom: 10px;
}

.chat-message-role {
  margin: 20px 0px 0px 15px;
  flex-shrink: 0;
  width: 60px;
  font-size: 12px;
  color: #999;
}

.chat-message-content {
  flex-grow: 1;
  padding: 10px;
  font-size: 14px;
  background-color: #f8f8f8;
  border-radius: 6px;
}


.chat-input {
  display: flex;
  margin-top: 10px;
}


.chat-input-box {
  flex-grow: 1;
  padding: 8px 12px;
  font-size: 14px;
  color: #555;
  background-color: #f8f8f8;
  border: 1px solid #ccc;
  border-radius: 4px;
  outline: none;
}

.chat-send-button {
  display: inline-block;
  width: 40px;
  height: 40px;
  margin-left: 10px;
  padding: 0;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.3s ease;
}


.chat-send-button:hover {
  background-color: #0069d9;
}

.apikey-input {
  display: flex;
  text-align: center;
  margin-top: 10px;
}

.api-save-button {
  display: inline-block;
  width: 40px;
  height: 40px;
  margin-left: 30px;
  // padding: 0;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.api-save-button:hover {
  background-color: #0069d9;
}

.api-setting-button {
  display: inline-block;
  width: 40px;
  height: 40px;
  margin-left: 10px;
  padding: 0;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.3s ease;
}

.api-setting-button:hover {
  background-color: #0069d9;
}
</style>