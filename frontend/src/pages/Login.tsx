import { zodResolver } from '@hookform/resolvers/zod'
import { Controller, useForm } from 'react-hook-form'
import z from 'zod'

import { Button } from '@/components/ui/button'

import {
  Field,
  FieldError,
  FieldGroup,
  FieldLabel,
} from '@/components/ui/field'
import { Input } from '@/components/ui/input'

const formSchema = z.object({
  username: z
    .string()
    .min(5, 'Username must be at least 5 characters')
    .max(32, 'Username must be at most 32 characters'),
  password: z
    .string()
    .min(5, 'Password must be at least 5 characters')
    .max(32, 'Password must be at most 32 characters'),
})

export default function Login() {
  const form = useForm<z.infer<typeof formSchema>>({
    resolver: zodResolver(formSchema),
    defaultValues: {
      username: '',
      password: '',
    },
  })

  function onSubmit(data: z.infer<typeof formSchema>) {
    console.log(data)
  }

  return (
    <>
      <form id='form' onSubmit={form.handleSubmit(onSubmit)}>
        <FieldGroup>
          <Controller
            name='username'
            control={form.control}
            render={({ field, fieldState }) => (
              <Field data-invalid={fieldState.invalid}>
                <FieldLabel htmlFor='form-username'>Username</FieldLabel>
                <Input
                  {...field}
                  id='form-username'
                  aria-invalid={fieldState.invalid}
                  placeholder='Username'
                  autoComplete='off'
                />
                {fieldState.invalid && (
                  <FieldError errors={[fieldState.error]} />
                )}
              </Field>
            )}
          />
        </FieldGroup>
        <FieldGroup>
          <Controller
            name='password'
            control={form.control}
            render={({ field, fieldState }) => (
              <Field data-invalid={fieldState.invalid}>
                <FieldLabel htmlFor='form-password'>Password</FieldLabel>
                <Input
                  {...field}
                  id='form-password'
                  aria-invalid={fieldState.invalid}
                  placeholder='Password'
                  autoComplete='off'
                />
                {fieldState.invalid && (
                  <FieldError errors={[fieldState.error]} />
                )}
              </Field>
            )}
          />
        </FieldGroup>
      </form>
      <Field orientation='horizontal'>
        <Button type='button' variant='outline' onClick={() => form.reset()}>
          Reset
        </Button>
        <Button type='submit' form='form'>
          Submit
        </Button>
      </Field>
    </>
  )
}
