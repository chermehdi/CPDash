<template>
  <div class="content ui grid">
    <div class="row" v-if="sheets.length > 0">
      <div class="column five wide scrollable">
        <div class="ui items">
          <div class="item" v-for="(sheet, index) in sheets" @click="showDetail(index)">
            <div class="content list-content">
              <div clas="sheet-name">{{ sheet.name }}</div>
            </div>
          </div>
        </div>
      </div>
      <div class="column eleven wide">
        <transition :before-enter="test" name="show" mode="out-in">
          <div class="sheet-details-container" :key="currentSelected.name">
            <div class="details-header">
              <div class="sheet-name">{{ currentSelected.name }}</div>
              <div class="sheet-description">{{ currentSelected.description }}</div>
            </div>
            <div class="details-content" :key="currentSelected.description">

            </div>
          </div>
        </transition>
      </div>
    </div>
    <div class="row" v-else>
      <div class="sixteen wide column center aligned instruction-container">
        <p class="instruction">You Don't Have any sheets Yet</p>
        <button class="ui button green" @click.prevent="createNewSheetForm">Create new Sheet</button>
      </div>
    </div>
    <div class="ui modal small" style="bottom: auto">
      <div class="header">
        Profile Picture
      </div>
      <div class="content" style="height: 100%">
        <form class="ui form">
          <div class="field required">
            <label for="fname">Sheet Name : </label>
            <input v-model="sheetName" id="fname" type="text" name="userName" placeholder="Sheet Name">
          </div>
          <div class="field required">
            <label for="desc">Sheet Description : </label>
            <input v-model="sheetDescription" id="desc" type="text" name="description" placeholder="Description">
          </div>
        </form>
      </div>
      <div class="actions">
        <div class="ui black deny button">
          Cancel
        </div>
        <div class="ui positive right labeled icon button" @click="addSheet">
          Create
          <i class="checkmark icon"></i>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        sheets: [],
        sheetName: '',
        sheetDescription: '',
        selected: {}
      }
    },
    methods: {
      test(el) {
        console.log('before enter', el)
      },
      createNewSheetForm() {
        $('.ui.modal.small').modal('show')
      },
      addSheet() {
        this.$store.commit('newSheet', {name: this.sheetName, description: this.sheetDescription})
        this.$store.commit('newSheet', {name: 'test', description: 'this is a topic about testing'})
        this.$store.commit('newSheet', {name: 'bitmasking', description: 'this is a topic about bitmasking'})
        this.clearLocalState()
        this.showDetail(0)
      },
      clearLocalState() {
        this.sheetName = ''
        this.sheetDescription = ''
      },
      showDetail(index) {
        this.selected = this.$store.state.sheets[index]
      }
    },
    computed: {
      currentSelected() {
        console.log('mounted ', this.selected)
        return this.selected
      }
    },
    mounted() {
      $('.ui.accordion').accordion();
      this.sheets = this.$store.state.sheets
    }
  }
</script>

<style lang="scss" scoped>
  @mixin good-font {
    font-family: 'Montserrat', sans-serif;
    text-transform: uppercase;
    font-weight: 300;
  }

  @mixin cardy-look {
    transition: all 0.3s cubic-bezier(0.215, 0.61, 0.355, 1) 0s, opacity 0.3s ease 0s, height 0s linear 0.35s;
    box-shadow: 0 2px rgba(17, 16, 15, 0.1), 0 2px 10px rgba(17, 16, 15, 0.1);
    border-radius: 3px;
  }

  h2.header {
    @include good-font;
  }

  .instruction-container {
    @include good-font;
    padding: 3em;
    margin-top: 5em;
    font-size: 3em;
  }

  .ui.items > .item > .content.list-content {
    @include good-font;
    @include cardy-look;
    padding: 1em 1rem;
    background-color: white;
    text-align: center;
    font-weight: 300;
    font-size: 1.3em;
    &:hover {
      cursor: pointer;
      background-color: #00BCD4;
      color: white;
      font-size: 1.5em;
      font-weight: 400;
    }
    &.clicked {
      background-color: #69F0AE;
      color: white;
      font-size: 1.5em;
      font-weight: 400;
    }
  }

  .column.wide .sheet-details-container {
    @include cardy-look;
    padding: 1em;
    background-color: white;
    .details-header {
      .sheet-name {
        font-weight: 400;
        font-size: 2em;
        padding: 1rem 0.5rem;
        color: #AB47BC;
      }
      .sheet-description {
        font-weight: 300;
        font-size: 1.3em;
        padding-left: 0.5rem;
      }
    }
  }

  .show-enter-active {
    transition: all .2s ease;
  }

  .show-leave-active {
    transition: all .6s cubic-bezier(1.0, 0.5, 0.8, 1.0);
  }

  .show-enter, .show-leave-to {
    opacity: 0;
  }
</style>

