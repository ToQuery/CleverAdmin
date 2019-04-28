<template>
  <button
    class="el-button"
    :disabled="disabled"
    :autofocus="autofocus"
    :type="nativeType"
    :size="size"
    :class="[
      type ? 'el-button--' + type : '',
      buttonSize ? 'el-button--' + buttonSize : '',
      {
        'is-disabled': disabled,
        'is-loading': loading,
        'is-plain': plain,
        'is-round': round
      },
      xClass ? xClass : ''
    ]"
    @click="handleClick"
  >
    <i v-if="loading" class="el-icon-loading" @click="handleInnerClick" />
    <i v-if="icon && !loading" :class="icon" @click="handleInnerClick" />
    <span v-if="$slots.default || text.length>0" @click="handleInnerClick"><slot>{{ text }}</slot></span>
  </button>
</template>
<script>
export default {
  // name: 'XButtonBase',

  inject: {
    elFormItem: {
      default: ''
    }
  },

  props: {
    text: {
      type: String,
      default: ''
    },
    type: {
      type: String,
      default: 'default'
    },
    size: String,
    icon: {
      type: String,
      default: ''
    },
    nativeType: {
      type: String,
      default: 'button'
    },
    loading: Boolean,
    disabled: Boolean,
    plain: Boolean,
    autofocus: Boolean,
    round: Boolean,
    xClass: {
      type: String,
      default: ''
    }
  },

  computed: {
    _elFormItemSize() {
      return (this.elFormItem || {}).elFormItemSize
    },
    buttonSize() {
      return this.size || this._elFormItemSize || (this.$ELEMENT || {}).size
    }
  },

  methods: {
    handleClick(evt) {
      this.$emit('click', evt)
    },
    handleInnerClick(evt) {
      if (this.disabled) {
        evt.stopPropagation()
      }
    }
  }
}
</script>
